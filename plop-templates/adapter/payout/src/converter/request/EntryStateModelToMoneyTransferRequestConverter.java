package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.converter.request;

import com.rbkmoney.adapter.common.utils.converter.PaymentDataConverter;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.MoneyTransferRequest;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Конвертация полей из EntryStateModel в поля для запросы к 3ей стороне.
 * Подпись запроса также может быть здесь.
 */
@Component
@RequiredArgsConstructor
public class EntryStateModelToMoneyTransferRequestConverter implements Converter<EntryStateModelImpl, MoneyTransferRequest> {

    @Override
    public MoneyTransferRequest convert(EntryStateModelImpl model) {
        final Map<String, String> options = model.getOptions();
        return MoneyTransferRequest.builder()
                //todo 3rd-party fields here
                .build();
    }
}
