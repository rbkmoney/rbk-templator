package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.handler

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.RemoteClient
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CancelRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CancelResponse
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel
import com.rbkmoney.adapter.common.enums.Step
import com.rbkmoney.adapter.common.processor.Processor
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CancelHandler(
    client: RemoteClient,
    converter: Converter<CustomEntryStateModel, CancelRequest>,
    processor: Processor<CustomExitStateModel, CancelResponse, CustomEntryStateModel>
) : StepHandler<CancelRequest, CancelResponse>(client::cancel, converter, processor) {

    override fun isHandle(entryStateModel: CustomEntryStateModel): Boolean {
        return entryStateModel.adapterContext.step == Step.CAPTURE
    }
}
