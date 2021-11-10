package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.request

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.AuthRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class EntryStateToAuthRequestConverter : Converter<CustomEntryStateModel, AuthRequest> {

    override fun convert(entryStateModel: CustomEntryStateModel): AuthRequest {
        return AuthRequest()
    }
}
