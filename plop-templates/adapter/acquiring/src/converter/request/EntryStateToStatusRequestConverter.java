package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.request;

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.StatusRequest;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntryStateToStatusRequestConverter implements Converter<CustomEntryStateModel, StatusRequest> {

    @Override
    public StatusRequest convert(CustomEntryStateModel entryStateModel) {
        return new StatusRequest();
    }
}
