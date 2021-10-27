package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.converter.request;

import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.StatusRequest;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
public class EntryStateModelToStatusRequestConverter implements Converter<EntryStateModelImpl, StatusRequest> {

    @SneakyThrows
    @Override
    public StatusRequest convert(EntryStateModelImpl model) {
        Map<String, String> options = model.getOptions();
        return StatusRequest.builder()
        {{#if_eq doc true}}//todo 3rd-party fields here{{/if_eq}}
                .build();
    }

}
