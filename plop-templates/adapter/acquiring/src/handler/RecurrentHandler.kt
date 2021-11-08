package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.handler

import com.rbkmoney.adapter.common.enums.Step
import com.rbkmoney.adapter.common.processor.Processor
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.RemoteClient
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.RecurrentRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.RecurrentResponse
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class RecurrentHandler(
    client: RemoteClient,
    converter: Converter<CustomEntryStateModel, RecurrentRequest>,
    processor: Processor<CustomExitStateModel, RecurrentResponse, CustomEntryStateModel>
) : StepHandler<RecurrentRequest, RecurrentResponse>(client::recurrent, converter, processor) {

    override fun isHandle(entryStateModel: CustomEntryStateModel): Boolean {
        return entryStateModel.adapterContext.step == Step.RECURRENT
    }
}
