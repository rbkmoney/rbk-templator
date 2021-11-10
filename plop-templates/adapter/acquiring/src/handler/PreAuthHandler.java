package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.handler;

import com.rbkmoney.adapter.common.enums.Step;
import com.rbkmoney.adapter.common.processor.Processor;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.RemoteClient;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.PreAuthRequest;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.PreAuthResponse;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PreAuthHandler extends StepHandler<PreAuthRequest, PreAuthResponse> {

    public PreAuthHandler(RemoteClient client,
                          Converter<CustomEntryStateModel, PreAuthRequest> converter,
                          Processor<CustomExitStateModel, PreAuthResponse, CustomEntryStateModel> processor) {
        super(client::preAuth, converter, processor);
    }

    @Override
    public boolean isHandle(CustomEntryStateModel entryStateModel) {
        return entryStateModel.getAdapterContext().getStep() == Step.PRE_AUTH;
    }

}
