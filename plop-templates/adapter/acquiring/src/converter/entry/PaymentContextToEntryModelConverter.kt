package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.entry

import com.rbkmoney.adapter.common.enums.TargetStatus
import com.rbkmoney.adapter.common.state.deserializer.AdapterDeserializer
import com.rbkmoney.adapter.common.state.utils.AdapterStateUtils
import com.rbkmoney.adapter.common.utils.converter.CardDataUtils
import com.rbkmoney.adapter.common.utils.converter.PaymentDataConverter
import com.rbkmoney.adapter.common.utils.converter.TargetStatusResolver
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.constant.AdapterConstants
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import com.rbkmoney.cds.client.storage.CdsClientStorage
import com.rbkmoney.cds.client.storage.utils.BankCardExtractor
import com.rbkmoney.cds.storage.SessionData
import com.rbkmoney.damsel.domain.TargetInvoicePaymentStatus
import com.rbkmoney.damsel.proxy_provider.Cash
import com.rbkmoney.damsel.proxy_provider.InvoicePayment
import com.rbkmoney.damsel.proxy_provider.PaymentContext
import com.rbkmoney.damsel.proxy_provider.PaymentInfo
import com.rbkmoney.damsel.proxy_provider.PaymentResource
import com.rbkmoney.java.cds.utils.model.CardDataProxyModel
import com.rbkmoney.java.damsel.utils.creators.ProxyProviderPackageCreators
import com.rbkmoney.java.damsel.utils.extractors.ProxyProviderPackageExtractors
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class PaymentContextToEntryModelConverter(
    private val adapterDeserializer: AdapterDeserializer,
    private val cdsStorage: CdsClientStorage
) : Converter<PaymentContext, CustomEntryStateModel> {

    override fun convert(paymentContext: PaymentContext): CustomEntryStateModel {
        val targetStatus = TargetStatusResolver.extractTargetStatus(paymentContext.getSession().getTarget())
        val paymentResource = paymentContext.paymentInfo.getPayment().paymentResource
        var cardData: CardDataProxyModel? = null
        var sessionData: SessionData? = null
        if (TargetStatus.PROCESSED == targetStatus) {
            if (!paymentResource.isSetRecurrentPaymentResource) {
                sessionData = cdsStorage.getSessionData(paymentContext)
            }
            cardData = getCardData(paymentContext, paymentResource)
        }
        val adapterContext = AdapterStateUtils.getAdapterContext(paymentContext, adapterDeserializer)
        adapterContext.options = adapterContext.options ?: mapOf()

        return CustomEntryStateModel().apply {
            val payment: InvoicePayment = paymentContext.paymentInfo.getPayment()
            val target: TargetInvoicePaymentStatus = paymentContext.getSession().getTarget()
            trxId = payment.getTrx()?.getId() ?: adapterContext.trxId
            trxExtra = payment.getTrx()?.getExtra() ?: mapOf()
            orderId = ProxyProviderPackageCreators.createInvoiceWithPayment(paymentContext.paymentInfo, "-")
            val cash = getCash(paymentContext.paymentInfo, payment, target)
            amount = cash.getAmount()
            refundAmount = cash.getAmount()
            currencySymbolCode = payment.getCost().getCurrency().symbolicCode
            createdAt = payment.createdAt
            pan = cardData?.pan
            cvv2 = CardDataUtils.extractCvv2(sessionData)
            expYear = cardData?.expYear
            expMonth = cardData?.expMonth
            cardHolder = cardData?.cardholderName
            email = PaymentDataConverter.extractEmail(paymentContext.paymentInfo)
            options = paymentContext.getOptions()
            callbackUrl = getRedirectUrl(paymentContext)
            this.adapterContext = adapterContext
            this.targetStatus = targetStatus
        }
    }

    private fun getCardData(context: PaymentContext, paymentResource: PaymentResource): CardDataProxyModel =
        if (paymentResource.isSetDisposablePaymentResource) {
            val cardToken = ProxyProviderPackageExtractors.extractBankCardToken(paymentResource)
            val cardData = cdsStorage.getCardData(cardToken)
            val bankCard = ProxyProviderPackageExtractors.extractBankCard(context)
            BankCardExtractor.initCardDataProxyModel(bankCard, cardData)
        } else cdsStorage.getCardData(context)

    private fun getCash(
        paymentInfo: PaymentInfo,
        payment: InvoicePayment,
        target: TargetInvoicePaymentStatus,
    ): Cash = when {
        target.isSetCaptured && paymentInfo.isSetCapture -> {
            paymentInfo.getCapture().getCost()
        }
        target.isSetRefunded && paymentInfo.isSetRefund -> {
            paymentInfo.getRefund().getCash()
        }
        else -> {
            payment.getCost()
        }
    }

    private fun getRedirectUrl(paymentContext: PaymentContext): String =
        paymentContext.paymentInfo?.payment?.payerSessionInfo?.redirectUrl ?: AdapterConstants.FINISH_INTERACTION_URL
}
