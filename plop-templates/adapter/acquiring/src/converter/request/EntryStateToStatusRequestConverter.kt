package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.request

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.StatusRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class EntryStateToStatusRequestConverter : Converter<CustomEntryStateModel, StatusRequest> {

    override fun convert(entryStateModel: CustomEntryStateModel): StatusRequest {
        return StatusRequest()
    }
}
