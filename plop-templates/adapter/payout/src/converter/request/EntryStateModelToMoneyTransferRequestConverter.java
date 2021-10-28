package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.converter.request;

import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.MoneyTransferRequest;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;

{{#if_eq doc true}}
/**
 * Конвертация полей из EntryStateModel в поля для запросы к 3ей стороне.
 * Подпись запроса также может быть здесь.
 */
{{/if_eq}}
@Component
@RequiredArgsConstructor
public class EntryStateModelToMoneyTransferRequestConverter
        implements Converter<EntryStateModelImpl, MoneyTransferRequest> {

    @Override
    public MoneyTransferRequest convert(EntryStateModelImpl model) {
        final Map<String, String> options = model.getOptions();
        return MoneyTransferRequest.builder()
                {{#if_eq doc true}}
                //todo 3rd-party fields here
                {{/if_eq}}
                .build();
    }
}
