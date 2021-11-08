package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.entry;

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.constant.AdapterConstants;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import com.rbkmoney.adapter.common.enums.TargetStatus;
import com.rbkmoney.adapter.common.model.AdapterContext;
import com.rbkmoney.adapter.common.state.deserializer.AdapterDeserializer;
import com.rbkmoney.adapter.common.state.utils.AdapterStateUtils;
import com.rbkmoney.adapter.common.utils.converter.CardDataUtils;
import com.rbkmoney.adapter.common.utils.converter.PaymentDataConverter;
import com.rbkmoney.adapter.common.utils.converter.TargetStatusResolver;
import com.rbkmoney.cds.client.storage.CdsClientStorage;
import com.rbkmoney.cds.client.storage.utils.BankCardExtractor;
import com.rbkmoney.cds.storage.CardData;
import com.rbkmoney.cds.storage.SessionData;
import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.PayerSessionInfo;
import com.rbkmoney.damsel.domain.TargetInvoicePaymentStatus;
import com.rbkmoney.damsel.proxy_provider.*;
import com.rbkmoney.java.cds.utils.model.CardDataProxyModel;
import com.rbkmoney.java.damsel.utils.creators.ProxyProviderPackageCreators;
import com.rbkmoney.java.damsel.utils.extractors.ProxyProviderPackageExtractors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
class PaymentContextToEntryModelConverter implements Converter<PaymentContext, CustomEntryStateModel> {

    private final CdsClientStorage cdsStorage;
    private final AdapterDeserializer adapterDeserializer;

    @Override
    public CustomEntryStateModel convert(PaymentContext paymentContext) {
        TargetStatus targetStatus = TargetStatusResolver.extractTargetStatus(paymentContext.getSession().getTarget());
        PaymentResource paymentResource = paymentContext.getPaymentInfo().getPayment().getPaymentResource();
        CardDataProxyModel cardData = null;
        SessionData sessionData = null;
        if (TargetStatus.PROCESSED == targetStatus) {
            if (!paymentResource.isSetRecurrentPaymentResource()) {
                sessionData = cdsStorage.getSessionData(paymentContext);
            }
            cardData = getCardData(paymentContext, paymentResource);
        }
        AdapterContext adapterContext = AdapterStateUtils.getAdapterContext(paymentContext, adapterDeserializer);
        adapterContext.setOptions(
                adapterContext.getOptions() != null ? adapterContext.getOptions() : Map.<String, String>of()
        );

        InvoicePayment payment = paymentContext.getPaymentInfo().getPayment();

        CustomEntryStateModel customEntryStateModel = new CustomEntryStateModel();
        customEntryStateModel.setTrxId(
                payment.getTrx() != null ? payment.getTrx().getId() : adapterContext.getTrxId()
        );
        customEntryStateModel.setTrxExtra(
                payment.getTrx() != null ? payment.getTrx().getExtra() : new HashMap<>()
        );
        customEntryStateModel.setOrderId(
                ProxyProviderPackageCreators.createInvoiceWithPayment(paymentContext.getPaymentInfo(), "-")
        );
        TargetInvoicePaymentStatus target = paymentContext.getSession().getTarget();
        Cash cash = getCash(paymentContext.getPaymentInfo(), payment, target);
        customEntryStateModel.setAmount(cash.getAmount());
        customEntryStateModel.setRefundAmount(cash.getAmount());
        customEntryStateModel.setCurrencySymbolCode(payment.getCost().getCurrency().getSymbolicCode());
        customEntryStateModel.setCreatedAt(payment.getCreatedAt());
        customEntryStateModel.setCvv2(CardDataUtils.extractCvv2(sessionData));
        if (cardData != null) {
            customEntryStateModel.setPan(cardData.getPan());
            customEntryStateModel.setExpYear(cardData.getExpYear());
            customEntryStateModel.setExpMonth(cardData.getExpMonth());
            customEntryStateModel.setCardHolder(cardData.getCardholderName());
        }
        customEntryStateModel.setEmail(PaymentDataConverter.extractEmail(paymentContext.getPaymentInfo()));
        customEntryStateModel.setCallbackUrl(getRedirectUrl(paymentContext));
        customEntryStateModel.setAdapterContext(adapterContext);
        customEntryStateModel.setTargetStatus(targetStatus);

        return customEntryStateModel;
    }

    private CardDataProxyModel getCardData(PaymentContext context, PaymentResource paymentResource) {
        if (paymentResource.isSetDisposablePaymentResource()) {
            String cardToken = ProxyProviderPackageExtractors.extractBankCardToken(paymentResource);
            CardData cardData = cdsStorage.getCardData(cardToken);
            BankCard bankCard = ProxyProviderPackageExtractors.extractBankCard(context);
            return BankCardExtractor.initCardDataProxyModel(bankCard, cardData);
        }

        return cdsStorage.getCardData(context);
    }

    private Cash getCash(
            PaymentInfo paymentInfo,
            InvoicePayment payment,
            TargetInvoicePaymentStatus target) {
        if (target.isSetCaptured() && paymentInfo.isSetCapture()) {
            return paymentInfo.getCapture().getCost();
        } else if (target.isSetRefunded() && paymentInfo.isSetRefund()) {
            return paymentInfo.getRefund().getCash();
        } else {
            return payment.getCost();
        }
    }

    private String getRedirectUrl(PaymentContext paymentContext) {
        PayerSessionInfo payerSessionInfo = paymentContext.getPaymentInfo().getPayment().getPayerSessionInfo();
        if (payerSessionInfo != null) {
            return paymentContext.getPaymentInfo().getPayment().getPayerSessionInfo().getRedirectUrl();
        }
        return AdapterConstants.FINISH_INTERACTION_URL;
    }

}
