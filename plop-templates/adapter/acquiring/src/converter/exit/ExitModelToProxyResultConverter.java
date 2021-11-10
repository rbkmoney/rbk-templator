package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.exit;

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.backoff.SleepIntentHelper;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel;
import com.rbkmoney.adapter.common.enums.Step;
import com.rbkmoney.adapter.common.model.AdapterContext;
import com.rbkmoney.adapter.common.state.serializer.AdapterSerializer;
import com.rbkmoney.damsel.domain.Failure;
import com.rbkmoney.damsel.proxy_provider.Intent;
import com.rbkmoney.damsel.proxy_provider.PaymentProxyResult;
import com.rbkmoney.damsel.user_interaction.UserInteraction;
import com.rbkmoney.error.mapping.ErrorMapping;
import com.rbkmoney.java.damsel.constant.Error;
import com.rbkmoney.java.damsel.utils.creators.DomainPackageCreators;
import com.rbkmoney.java.damsel.utils.creators.ProxyProviderPackageCreators;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static com.rbkmoney.java.damsel.utils.creators.ProxyProviderPackageCreators.createIntentWithSleepIntent;
import static com.rbkmoney.java.damsel.utils.creators.ProxyProviderPackageCreators.createProxyResultFailure;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExitModelToProxyResultConverter implements Converter<CustomExitStateModel, PaymentProxyResult> {

    private final ErrorMapping errorMapping;

    private final AdapterSerializer adapterSerializer;

    private final SleepIntentHelper sleepIntentHelper;

    @Override
    public PaymentProxyResult convert(CustomExitStateModel exitStateModel) {
        log.info("PaymentProxy exit state model: {}", exitStateModel);
        PaymentProxyResult errorResult = checkOnError(exitStateModel);
        if (errorResult != null) {
            return errorResult;
        }
        AdapterContext adapterContext = exitStateModel.getAdapterContext();
        Step nextStep = exitStateModel.getAdapterContext().getStep();
        Intent intent;
        switch (nextStep) {
            case AUTH:
                intent = createIntentWithSleepIntent(0);
                break;
            case FINISH_THREE_DS:
                intent = createIntentWithSuspendIntent(exitStateModel);
                break;
            case CHECK_STATUS:
                intent = sleepIntentHelper.build(
                        adapterContext, exitStateModel.getGeneralEntryStateModel().getOptions()
                );
                break;
                {{#if_eq doc true}}
                // Тут можно добавить проверку статуса от 3-й стороны.
                // И в зависимости от этого продолжать запрашивать статус или завершить взаимодействие.
                // Пример:
                {{/if_eq}}
                // switch (existStateModel.status) {
                //     case SUCCESS:
                //         intent = ProxyProviderPackageCreators.createFinishIntentSuccess();
                //         break;
                //     case ERROR:
                //         ProxyProviderPackageCreators.createFinishIntentFailure(
                //             errorMapping.mapFailure(exitStateModel.status.toString())
                //         )
            default:
                throw new IllegalStateException("Wrong next step: " + nextStep);
        }

        PaymentProxyResult paymentProxyResult = new PaymentProxyResult(intent)
                .setNextState(adapterSerializer.writeByte(adapterContext));
        paymentProxyResult.setTrx(
                DomainPackageCreators.createTransactionInfo(
                        exitStateModel.getThirdPartyId(),
                        exitStateModel.getTrxExtra()
                )
        );
        log.info("Payment proxy result: {}", paymentProxyResult);

        return paymentProxyResult;
    }

    private Intent createIntentWithSuspendIntent(CustomExitStateModel exitStateModel) {
        AdapterContext adapterContext = exitStateModel.getAdapterContext();
        UserInteraction userInteraction = exitStateModel.getThreeDsUrl() != null
                ? UserInteraction.redirect(
                        ProxyProviderPackageCreators.createBrowserGetRequest(exitStateModel.getThreeDsUrl())
                ) : null;

        return sleepIntentHelper.build(
                adapterContext,
                exitStateModel.getGeneralEntryStateModel().getOptions(),
                userInteraction);
    }

    private PaymentProxyResult checkOnError(CustomExitStateModel exitStateModel) {
        if (!StringUtils.hasLength(exitStateModel.getErrorCode())
                || !StringUtils.hasLength(exitStateModel.getErrorMessage())) {
            Failure failure;
            if (!StringUtils.hasLength(exitStateModel.getErrorCode())) {
                failure = errorMapping.mapFailure(exitStateModel.getErrorCode());
                failure.setReason(exitStateModel.getErrorMessage());
            } else {
                failure = errorMapping.mapFailure(Error.DEFAULT_ERROR_CODE);
            }
            return createProxyResultFailure(failure);
        }
        return null;
    }

}
