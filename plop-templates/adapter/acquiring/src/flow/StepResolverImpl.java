package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.flow;

import com.rbkmoney.adapter.bank.spring.boot.starter.flow.StepResolver;
import com.rbkmoney.adapter.bank.spring.boot.starter.model.GeneralEntryStateModel;
import com.rbkmoney.adapter.common.enums.Step;
import com.rbkmoney.adapter.common.enums.TargetStatus;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

{{#if_eq doc true}}
/**
 * Здесь определяется следующий Step для работы адаптера на основе полей из Model.
 * Можно использовать статусы ответа 3ей стороны, какие-либо поля, на основании которых можно принять решение о
 * следующем шаге.
 *
 * @see com.rbkmoney.adapter.common.enums.Step
 */
{{/if_eq}}
@Slf4j
@Component
public class StepResolverImpl implements StepResolver<CustomEntryStateModel, CustomExitStateModel> {

    @Override
    public Step resolveEntry(CustomEntryStateModel entryStateModel) {
        TargetStatus targetStatus = entryStateModel.getTargetStatus();
        Step step = entryStateModel.getAdapterContext().getStep();

        if (targetStatus == null) {
            return Objects.requireNonNullElse(step, Step.PRE_AUTH);
        }

        log.info("Entry resolver. Target status: {}. Step: {}", targetStatus, step);
        switch (targetStatus) {
            case PROCESSED:
                if (entryStateModel.getRecToken() != null) {
                    return Step.AUTH_RECURRENT;
                }
                return Objects.requireNonNullElse(step, Step.PRE_AUTH);
            case CAPTURED:
                return Step.CAPTURE;
            case CANCELLED:
                return Objects.requireNonNullElse(step, Step.CANCEL);
            case REFUNDED:
                return Objects.requireNonNullElse(step, Step.REFUND);
            default:
                throw new IllegalStateException("Unknown target status: " + targetStatus);
        }
    }

    @Override
    public Step resolveExit(CustomExitStateModel exitStateModel) {
        GeneralEntryStateModel entryStateModel = exitStateModel.getGeneralEntryStateModel();
        Step step = entryStateModel.getAdapterContext().getStep();
        log.info("Exit resolver. Step: {}", step);
        return step;
    }

}
