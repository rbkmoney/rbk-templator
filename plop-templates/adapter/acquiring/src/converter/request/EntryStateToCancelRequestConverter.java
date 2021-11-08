package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.converter.request;

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CancelRequest;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntryStateToCancelRequestConverter implements Converter<CustomEntryStateModel, CancelRequest> {

    @Override
    public CancelRequest convert(CustomEntryStateModel entryStateModel) {
        return new CancelRequest();
    }
}
