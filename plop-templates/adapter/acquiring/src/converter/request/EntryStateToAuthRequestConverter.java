package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.request;

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.AuthRequest;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntryStateToAuthRequestConverter implements Converter<CustomEntryStateModel, AuthRequest> {

    @Override
    public AuthRequest convert(CustomEntryStateModel entryStateModel) {
        return new AuthRequest();
    }
}
