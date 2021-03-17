package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.converter.request;

import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.StatusRequest;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.constant.OptionsField;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * Конвертация полей из EntryStateModel в поля для запросы к 3ей стороне.
 * Подпись запроса также может быть здесь.
 */
@Component
@RequiredArgsConstructor
public class EntryStateModelToStatusRequestConverter implements Converter<EntryStateModelImpl, StatusRequest> {

    @SneakyThrows
    @Override
    public StatusRequest convert(EntryStateModelImpl model) {
        Map<String, String> options = model.getOptions();
        return StatusRequest.builder()
                //todo 3rd-party fields here
                .build();
    }

}
