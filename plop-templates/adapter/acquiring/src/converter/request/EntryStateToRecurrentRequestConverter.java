package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.request;

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.RecurrentRequest;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntryStateToRecurrentRequestConverter implements Converter<CustomEntryStateModel, RecurrentRequest> {

    @Override
    public RecurrentRequest convert(CustomEntryStateModel entryStateModel) {
        return new RecurrentRequest();
    }
}
