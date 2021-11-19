package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.service;

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel;
import com.rbkmoney.adapter.bank.spring.boot.starter.flow.StepResolver;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.validator.ValidatorImpl;
import com.rbkmoney.adapter.common.handler.CommonHandler;
import com.rbkmoney.damsel.proxy_provider.*;
import lombok.RequiredArgsConstructor;
import org.apache.thrift.TException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Adapter{{properCase bank_name}}ProviderProxy implements ProviderProxySrv.Iface {

    private final ValidatorImpl paymentContextValidator;

    private final Converter<PaymentContext, CustomEntryStateModel> ctxToEntryModelConverter;

    private final Converter<CustomExitStateModel, PaymentProxyResult> exitModelToProxyResultConverter;

    private final StepResolver<CustomEntryStateModel, CustomExitStateModel> stepResolver;

    private final List<CommonHandler<CustomExitStateModel, CustomEntryStateModel>> handlers;

    @Override
    public RecurrentTokenProxyResult generateToken(RecurrentTokenContext recurrentTokenContext) throws TException {
{{#if_eq doc true}}
//        Пример кода:
//        recurrentTokenContextValidator.validate(recurrentTokenContext);
//
//        CustomEntryStateModel entryStateModel = recCtxToEntryModelConverter.convert(recurrentTokenContext);
//        entryStateModel.getAdapterContext().setStep(stepResolver.resolveEntry(entryStateModel));
//
//        CommonHandler<CustomExitStateModel, CustomEntryStateModel> handler = findHandler(entryStateModel);
//
//        CustomExitStateModel exitStateModel = handler.handle(entryStateModel);
//        exitStateModel.getAdapterContext().setStep(stepResolver.resolveExit(exitStateModel));
//
//        return exitModelRecurrentTokenProxyResultConverter.convert(exitStateModel);
{{/if_eq}}
        throw new IllegalStateException("Unsupported method");
    }

    @Override
    public RecurrentTokenCallbackResult handleRecurrentTokenCallback(
            ByteBuffer byteBuffer,
            RecurrentTokenContext recurrentTokenContext
    ) throws TException {
{{#if_eq doc true}}
//        recurrentTokenContextValidator.validate(recurrentTokenContext);
//
//        AdapterContext adapterContext = AdapterStateUtils.getAdapterContext(
//              recurrentTokenContext, adapterDeserializer
//        );
//        adapterContext.setStep(Step.GENERATE_TOKEN_FINISH_THREE_DS);
//        Callback callbackObj = callbackDeserializer.read(byteBuffer.array());
//        adapterContext.setPaRes(callbackObj.getPaRes());
//        adapterContext.setMd(callbackObj.getMd());
//
//        byte[] callbackResponse = new byte[0];
//        return createRecurrentTokenCallbackResult(
//                callbackResponse,
//                new RecurrentTokenProxyResult()
//                        .setIntent(RecurrentTokenIntent.sleep(createSleepIntent(createTimerTimeout(0))))
//                        .setNextState(adapterSerializer.writeByte(adapterContext))
//        );
{{/if_eq}}
        throw new IllegalStateException("Unsupported method");
    }

    @Override
    public PaymentProxyResult processPayment(PaymentContext paymentContext) throws TException {
        paymentContextValidator.validate(paymentContext);

        CustomEntryStateModel entryStateModel = ctxToEntryModelConverter.convert(paymentContext);
        entryStateModel.getAdapterContext().setStep(stepResolver.resolveEntry(entryStateModel));

        CommonHandler<CustomExitStateModel, CustomEntryStateModel> handler = findHandler(entryStateModel);

        CustomExitStateModel exitStateModel = handler.handle(entryStateModel);
        exitStateModel.getAdapterContext().setStep(stepResolver.resolveExit(exitStateModel));

        return exitModelToProxyResultConverter.convert(exitStateModel);
    }

    @Override
    public PaymentCallbackResult handlePaymentCallback(
            ByteBuffer byteBuffer,
            PaymentContext paymentContext
    ) throws TException {
{{#if_eq doc true}}
//        AdapterContext adapterContext = AdapterStateUtils.getAdapterContext(paymentContext, adapterDeserializer);
//        adapterContext.setStep(Step.CHECK_STATUS);
//        Callback callbackObj = callbackDeserializer.read(byteBuffer.array());
//        adapterContext.setPaRes(callbackObj.getPaRes());
//        adapterContext.setMd(callbackObj.getMd());
//
//        byte[] callbackResponse = new byte[0];
//        return createCallbackResult(
//                callbackResponse,
//                new PaymentCallbackProxyResult()
//                        .setIntent(createIntentWithSleepIntent(0))
//                        .setNextState(adapterSerializer.writeByte(adapterContext))
//        );
{{/if_eq}}
        throw new IllegalStateException("Unsupported method");
    }

    private CommonHandler<CustomExitStateModel, CustomEntryStateModel> findHandler(
            CustomEntryStateModel entryStateModel
    ) throws TException {
        return handlers.stream()
                .filter(handler -> handler.isHandle(entryStateModel))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Not found handler for state: " + entryStateModel));
    }

}
