package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.request

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CaptureRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class EntryStateToCaptureRequestConverter : Converter<CustomEntryStateModel, CaptureRequest> {

    override fun convert(entryStateModel: CustomEntryStateModel): CaptureRequest {
        return CaptureRequest()
    }
}
