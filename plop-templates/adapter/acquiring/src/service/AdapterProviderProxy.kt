package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.service

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.spring.boot.starter.flow.StepResolver
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.validator.ValidatorImpl
import com.rbkmoney.adapter.common.handler.CommonHandler
import com.rbkmoney.damsel.proxy_provider.PaymentCallbackResult
import com.rbkmoney.damsel.proxy_provider.PaymentContext
import com.rbkmoney.damsel.proxy_provider.PaymentProxyResult
import com.rbkmoney.damsel.proxy_provider.ProviderProxySrv
import com.rbkmoney.damsel.proxy_provider.RecurrentTokenCallbackResult
import com.rbkmoney.damsel.proxy_provider.RecurrentTokenContext
import com.rbkmoney.damsel.proxy_provider.RecurrentTokenProxyResult
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Service
import java.nio.ByteBuffer

@Service
class Adapter{{properCase bank_name}}ProviderProxy(
    private val ctxToEntryModelConverter: Converter<PaymentContext, CustomEntryStateModel>,
    private val exitStateModelConverter: Converter<CustomExitStateModel, PaymentProxyResult>,
    private val paymentContextValidator: ValidatorImpl,
    private val stepResolver: StepResolver<CustomEntryStateModel, CustomExitStateModel>,
    private val handlers: List<CommonHandler<CustomExitStateModel, CustomEntryStateModel>>,
) : ProviderProxySrv.Iface {

    override fun generateToken(recurrentTokenContext: RecurrentTokenContext): RecurrentTokenProxyResult {
{{#if_eq doc true}}
//        Пример кода:
//        recurrentTokenContextValidator.validate(recurrentTokenContext)
//
//        val entryStateModel: GeneralEntryStateModel = recCtxToEntryModelConverter.convert(recurrentTokenContext)
//        entryStateModel.adapterContext.step = stepResolver.resolveEntry(entryStateModel)
//
//        val handler: CommonHandler<CustomExitStateModel, CustomEntryStateModel> = findHandler(entryStateModel)
//
//        val exitStateModel = handler.handle(entryStateModel)
//        exitStateModel.adapterContext.step = stepResolver.resolveExit(exitStateModel)
//
//        return exitModelRecurrentTokenProxyResultConverter.convert(exitStateModel)
{{/if_eq}}
        throw NotImplementedError("Not yet implemented")
    }

    override fun handleRecurrentTokenCallback(
        byteBuffer: ByteBuffer,
        recurrentTokenContext: RecurrentTokenContext,
    ): RecurrentTokenCallbackResult {
{{#if_eq doc true}}
//        Пример кода:
//        recurrentTokenContextValidator.validate(recurrentTokenContext)
//
//        val adapterContext = AdapterStateUtils.getAdapterContext(recurrentTokenContext, adapterDeserializer)
//        adapterContext.step = Step.GENERATE_TOKEN_FINISH_THREE_DS
//        val callbackObj: Callback = callbackDeserializer.read(byteBuffer.array())
//        adapterContext.paRes = callbackObj.paRes
//        adapterContext.md = callbackObj.md
//
//        val callbackResponse = ByteArray(0)
//        return ProxyProviderPackageCreators.createRecurrentTokenCallbackResult(
//            callbackResponse,
//            RecurrentTokenProxyResult()
//                .setIntent(
//                    RecurrentTokenIntent.sleep(
//                        ProxyProviderPackageCreators.createSleepIntent(
//                            BasePackageCreators.createTimerTimeout(0)
//                        )
//                    )
//                )
//                .setNextState(adapterSerializer.writeByte(adapterContext))
//        )
{{/if_eq}}
        throw NotImplementedError("Not yet implemented")
    }

    override fun processPayment(paymentContext: PaymentContext): PaymentProxyResult {
        paymentContextValidator.validate(paymentContext)
        val entryStateModel = ctxToEntryModelConverter.convert(paymentContext)!!
        entryStateModel.adapterContext.step = stepResolver.resolveEntry(entryStateModel)
        val handler = findHandler(entryStateModel)
        val exitStateModel = handler.handle(entryStateModel)
        exitStateModel.adapterContext.step = stepResolver.resolveExit(exitStateModel)

        return exitStateModelConverter.convert(exitStateModel)!!
    }

    override fun handlePaymentCallback(
        byteBuffer: ByteBuffer,
        paymentContext: PaymentContext,
    ): PaymentCallbackResult {
{{#if_eq doc true}}
//        Пример кода:
//        val adapterContext = AdapterStateUtils.getAdapterContext(paymentContext, adapterDeserializer)
//        adapterContext.step = Step.CHECK_STATUS
//        val callbackObj: Callback = callbackDeserializer.read(byteBuffer.array())
//        adapterContext.paRes = callbackObj.paRes
//        adapterContext.md = callbackObj.md
//        val callbackResponse = ByteArray(0)
//        return ProxyProviderPackageCreators.createCallbackResult(
//            callbackResponse,
//            PaymentCallbackProxyResult()
//                .setIntent(ProxyProviderPackageCreators.createIntentWithSleepIntent(0))
//                .setNextState(adapterSerializer.writeByte(adapterContext))
//        )
{{/if_eq}}
        throw NotImplementedError("Not yet implemented")
    }

    private fun findHandler(
        entryStateModel: CustomEntryStateModel,
    ): CommonHandler<CustomExitStateModel, CustomEntryStateModel> {
        return handlers.find { it.isHandle(entryStateModel) }
            ?: throw IllegalStateException("Not found handler for state: $entryStateModel")
    }
}
