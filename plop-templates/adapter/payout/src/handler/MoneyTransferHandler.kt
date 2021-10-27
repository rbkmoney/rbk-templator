package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.handler

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.CommonHandlerImpl
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.Step
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.RemoteClient
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseResponse
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.MoneyTransferRequest
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.ExitStateModelImpl
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

{{#if_eq doc true}}
/**
 * Описание обработчика, конвертера, процессора для шага удовлетворяющего условию в isHandle - Step.PAYOUT.
 */
{{/if_eq}}
@Component
class MoneyTransferHandler(
    remoteClient: RemoteClient,
    converter: Converter<EntryStateModelImpl, MoneyTransferRequest>,
    responseProcessorChain: Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl>
) : CommonHandlerImpl<MoneyTransferRequest, BaseResponse, EntryStateModelImpl, ExitStateModelImpl>(
    remoteClient::moneyTransfer,
    converter,
    responseProcessorChain
) {
    override fun isHandle(entryStateModel: EntryStateModelImpl) =
        entryStateModel.state.step === Step.PAYOUT
}
