package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.handler

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.RemoteClient
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.StatusRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.StatusResponse
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel
import com.rbkmoney.adapter.common.enums.Step
import com.rbkmoney.adapter.common.processor.Processor
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class StatusHandler(
    client: RemoteClient,
    converter: Converter<CustomEntryStateModel, StatusRequest>,
    processor: Processor<CustomExitStateModel, StatusResponse, CustomEntryStateModel>
) : StepHandler<StatusRequest, StatusResponse>(client::status, converter, processor) {

    override fun isHandle(entryStateModel: CustomEntryStateModel): Boolean {
        return entryStateModel.adapterContext.step == Step.CHECK_STATUS
    }
}
