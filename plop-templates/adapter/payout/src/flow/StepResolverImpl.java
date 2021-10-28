package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.flow;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.flow.StepResolver;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.Step;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.ExitStateModelImpl;
import org.springframework.stereotype.Component;

import java.util.Objects;

{{#if_eq doc true}}
/**
 * Здесь определяется следующий Step для работы адаптера на основе полей из Model.
 * Можно использовать статусы ответа 3ей стороны, какие-либо поля, на основании которых можно принять решение о
 * следующем шаге.
 *
 * @see com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.Step
 */
{{/if_eq}}
@Component
public class StepResolverImpl implements StepResolver<EntryStateModelImpl, ExitStateModelImpl> {

    @Override
    public Step resolveEntry(EntryStateModelImpl entryStateModel) {
        return Objects.requireNonNullElse(entryStateModel.getState().getStep(), Step.PAYOUT);
    }

    @Override
    public Step resolveExit(ExitStateModelImpl exitStateModel) {
        return exitStateModel.getEntryStateModel().getState().getStep();
    }

}
