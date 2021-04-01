package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.converter.request

import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.StatusRequest
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component


/**
 * Конвертация полей из EntryStateModel в поля для запросы к 3ей стороне.
 * Подпись запроса также может быть здесь.
 */
@Component
class EntryStateModelToStatusRequestConverter : Converter<EntryStateModelImpl, StatusRequest> {
    override fun convert(model: EntryStateModelImpl): StatusRequest {
        val options: Map<String, String> = model.options
        return StatusRequest("") //todo 3rd-party fields here
    }
}
