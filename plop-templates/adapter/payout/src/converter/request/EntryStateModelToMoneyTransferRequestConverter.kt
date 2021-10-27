package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.converter.request

import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.MoneyTransferRequest
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

{{#if_eq doc true}}
/**
 * Конвертация полей из EntryStateModel в поля для запросы к 3ей стороне.
 * Подпись запроса также может быть здесь.
 */
{{/if_eq}}
@Component
class EntryStateModelToMoneyTransferRequestConverter : Converter<EntryStateModelImpl, MoneyTransferRequest> {
    override fun convert(model: EntryStateModelImpl): MoneyTransferRequest {
        val options = model.options
        return MoneyTransferRequest("") {{#if_eq doc true}} // todo 3rd-party fields here {{/if_eq}}
    }
}
