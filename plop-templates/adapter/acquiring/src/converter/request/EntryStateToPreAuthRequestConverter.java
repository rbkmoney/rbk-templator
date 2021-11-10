package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.request;

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.PreAuthRequest;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntryStateToPreAuthRequestConverter implements Converter<CustomEntryStateModel, PreAuthRequest> {

    @Override
    public PreAuthRequest convert(CustomEntryStateModel entryStateModel) {
        return new PreAuthRequest();
    }
}
