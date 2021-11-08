package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.request

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.PreAuthRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class EntryStateToPreAuthRequestConverter : Converter<CustomEntryStateModel, PreAuthRequest> {

    override fun convert(entryStateModel: CustomEntryStateModel): PreAuthRequest {
        return PreAuthRequest()
    }
}
