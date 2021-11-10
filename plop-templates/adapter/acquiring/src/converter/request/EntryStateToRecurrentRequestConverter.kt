package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.request

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.RecurrentRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class EntryStateToRecurrentRequestConverter : Converter<CustomEntryStateModel, RecurrentRequest> {

    override fun convert(entryStateModel: CustomEntryStateModel): RecurrentRequest {
        return RecurrentRequest()
    }
}
