package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.converter.request

import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.MoneyTransferRequest
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl
import lombok.RequiredArgsConstructor
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

/**
 * Конвертация полей из EntryStateModel в поля для запросы к 3ей стороне.
 * Подпись запроса также может быть здесь.
 */
@Component
class EntryStateModelToMoneyTransferRequestConverter : Converter<EntryStateModelImpl, MoneyTransferRequest> {
    override fun convert(model: EntryStateModelImpl): MoneyTransferRequest {
        val options = model.options
        return MoneyTransferRequest("") //todo 3rd-party fields here
    }
}
