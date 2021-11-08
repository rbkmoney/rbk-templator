package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.handler

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.RemoteClient
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CaptureRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CaptureResponse
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel
import com.rbkmoney.adapter.common.enums.Step
import com.rbkmoney.adapter.common.processor.Processor
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CaptureHandler(
    client: RemoteClient,
    converter: Converter<CustomEntryStateModel, CaptureRequest>,
    processor: Processor<CustomExitStateModel, CaptureResponse, CustomEntryStateModel>
) : StepHandler<CaptureRequest, CaptureResponse>(client::capture, converter, processor) {

    override fun isHandle(entryStateModel: CustomEntryStateModel): Boolean {
        return entryStateModel.adapterContext.step == Step.CAPTURE
    }
}
