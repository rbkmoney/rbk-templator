package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.exit

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}.backoff.SleepIntentHelper
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}.model.CustomExitStateModel
import com.rbkmoney.adapter.common.enums.Step
import com.rbkmoney.adapter.common.state.serializer.AdapterSerializer
import com.rbkmoney.damsel.domain.Failure
import com.rbkmoney.damsel.proxy_provider.Intent
import com.rbkmoney.damsel.proxy_provider.PaymentProxyResult
import com.rbkmoney.damsel.user_interaction.UserInteraction
import com.rbkmoney.error.mapping.ErrorMapping
import com.rbkmoney.java.damsel.constant.Error
import com.rbkmoney.java.damsel.utils.creators.DomainPackageCreators
import com.rbkmoney.java.damsel.utils.creators.ProxyProviderPackageCreators
import mu.KotlinLogging
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ExitModelToProxyResultConverter(
    private val errorMapping: ErrorMapping,
    private val adapterSerializer: AdapterSerializer,
    private val sleepIntentHelper: SleepIntentHelper,
) : Converter<CustomExitStateModel, PaymentProxyResult> {

    private val log = KotlinLogging.logger {}

    override fun convert(exitStateModel: CustomExitStateModel): PaymentProxyResult {
        log.info { "PaymentProxy exit state model: $exitStateModel" }
        checkOnError(exitStateModel)?.let {
            return it
        }
        val adapterContext = exitStateModel.adapterContext

        val intent = when (val nextStep = exitStateModel.adapterContext.step) {
            Step.AUTH -> ProxyProviderPackageCreators.createIntentWithSleepIntent(0)
            Step.FINISH_THREE_DS -> createIntentWithSuspendIntent(exitStateModel)
            Step.CHECK_STATUS -> {
                sleepIntentHelper.build(adapterContext, exitStateModel.generalEntryStateModel.options)
                {{#if_eq doc true}}
                // Тут можно добавить проверку статуса от 3-й стороны.
                // И в зависимости от этого продолжать запрашивать статус или завершить взаимодействие.
                // Пример:
                // when (exitStateModel.status) {
                //      TransactionStatus.SUCCESS -> {
                //          ProxyProviderPackageCreators.createFinishIntentSuccess()
                //      }
                //      TransactionStatus.ERROR -> {
                //          ProxyProviderPackageCreators.createFinishIntentFailure(
                //              errorMapping.mapFailure(exitStateModel.status.toString())
                //          )
                //       }
                //       else -> throw IllegalStateException("${exitStateModel.status} is unsupported!")
                //}
                {{/if_eq}}
            }
            Step.CAPTURE, Step.DO_NOTHING -> ProxyProviderPackageCreators.createFinishIntentSuccess()
            else -> throw IllegalStateException("Wrong next step: $nextStep")
        }

        val paymentProxyResult = PaymentProxyResult(intent).setNextState(adapterSerializer.writeByte(adapterContext))
        paymentProxyResult.setTrx(
            DomainPackageCreators.createTransactionInfo(
                exitStateModel.thirdPartyId,
                exitStateModel.trxExtra
            )
        )
        log.info { "Payment proxy result: $paymentProxyResult" }

        return paymentProxyResult
    }

    private fun checkOnError(exitStateModel: CustomExitStateModel): PaymentProxyResult? {
        if (!exitStateModel.errorCode.isNullOrEmpty() || !exitStateModel.errorMessage.isNullOrEmpty()) {
            val failure: Failure
            if (!exitStateModel.errorCode.isNullOrEmpty()) {
                failure = errorMapping.mapFailure(exitStateModel.errorCode)
                failure.setReason(exitStateModel.errorMessage)
            } else {
                failure = errorMapping.mapFailure(Error.DEFAULT_ERROR_CODE)
            }
            return ProxyProviderPackageCreators.createProxyResultFailure(failure)
        }
        return null
    }

    private fun createIntentWithSuspendIntent(exitStateModel: CustomExitStateModel): Intent {
        val adapterContext = exitStateModel.adapterContext
        val userInteraction = exitStateModel.threeDsUrl?.let {
            UserInteraction.redirect(ProxyProviderPackageCreators.createBrowserGetRequest(it))
        }
        return sleepIntentHelper.build(
            adapterContext,
            exitStateModel.generalEntryStateModel.options,
            userInteraction
        )
    }
}
