package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.handler

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.RemoteClient
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.PreAuthRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.PreAuthResponse
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}k.model.CustomExitStateModel
import com.rbkmoney.adapter.common.enums.Step
import com.rbkmoney.adapter.common.processor.Processor
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class PreAuthHandler(
    client: RemoteClient,
    converter: Converter<CustomEntryStateModel, PreAuthRequest>,
    processor: Processor<CustomExitStateModel, PreAuthResponse, CustomEntryStateModel>
) : StepHandler<PreAuthRequest, PreAuthResponse>(client::preAuth, converter, processor) {

    override fun isHandle(entryStateModel: CustomEntryStateModel): Boolean {
        return entryStateModel.adapterContext.step == Step.PRE_AUTH
    }
}
