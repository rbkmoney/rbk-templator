package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.request

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CancelRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class EntryStateToCancelRequestConverter : Converter<CustomEntryStateModel, CancelRequest> {

    override fun convert(entryStateModel: CustomEntryStateModel): CancelRequest {
        return CancelRequest()
    }
}
