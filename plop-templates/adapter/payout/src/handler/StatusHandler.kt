package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.handler

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.CommonHandlerImpl
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.Step
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.RemoteClient
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseResponse
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.StatusRequest
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.ExitStateModelImpl
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

{{#if_eq doc true}}
/**
 * Описание обработчика, конвертера, процессора для шага удовлетворяющего условию в isHandle - Step.СHECK.
 */
{{/if_eq}}
@Component
class StatusHandler(
    remoteClient: RemoteClient,
    converter: Converter<EntryStateModelImpl, StatusRequest>,
    responseProcessorChain: Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl>
) : CommonHandlerImpl<StatusRequest, BaseResponse, EntryStateModelImpl, ExitStateModelImpl>(
    remoteClient::status,
    converter,
    responseProcessorChain
) {
    override fun isHandle(entryStateModel: EntryStateModelImpl) =
        entryStateModel.state.step === Step.CHECK
}
