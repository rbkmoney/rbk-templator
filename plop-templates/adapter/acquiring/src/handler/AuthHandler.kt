package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.handler

import com.rbkmoney.adapter.common.enums.Step
import com.rbkmoney.adapter.common.processor.Processor
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.RemoteClient
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.AuthRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.AuthResponse
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class AuthHandler(
    client: RemoteClient,
    converter: Converter<CustomEntryStateModel, AuthRequest>,
    processor: Processor<CustomExitStateModel, AuthResponse, CustomEntryStateModel>
) : StepHandler<AuthRequest, AuthResponse>(client::auth, converter, processor) {

    override fun isHandle(entryStateModel: CustomEntryStateModel): Boolean {
        return entryStateModel.adapterContext.step == Step.AUTH
    }
}
