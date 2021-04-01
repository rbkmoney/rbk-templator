package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.flow

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.flow.StepResolver
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.Step
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.ExitStateModelImpl
import org.springframework.stereotype.Component

/**
 * Здесь определяется следующий Step для работы адаптера на основе полей из Model.
 * Можно использовать статусы ответа 3ей стороны, какие-либо поля, на основании которых можно принять решение о
 * следующем шаге.
 *
 * @see com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.Step
 */
@Component
class StepResolverImpl : StepResolver<EntryStateModelImpl, ExitStateModelImpl> {
    override fun resolveEntry(entryStateModel: EntryStateModelImpl): Step =
        entryStateModel.state.step ?: Step.PAYOUT

    override fun resolveExit(exitStateModel: ExitStateModelImpl): Step =
        exitStateModel.entryStateModel.state.step
}
