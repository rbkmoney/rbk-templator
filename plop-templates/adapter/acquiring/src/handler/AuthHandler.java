package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.handler;

import com.rbkmoney.adapter.common.enums.Step;
import com.rbkmoney.adapter.common.processor.Processor;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.RemoteClient;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.AuthRequest;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.AuthResponse;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthHandler extends StepHandler<AuthRequest, AuthResponse> {

    public AuthHandler(RemoteClient client,
                       Converter<CustomEntryStateModel, AuthRequest> converter,
                       Processor<CustomExitStateModel, AuthResponse, CustomEntryStateModel> processor) {
        super(client::auth, converter, processor);
    }

    @Override
    public boolean isHandle(CustomEntryStateModel entryStateModel) {
        return entryStateModel.getAdapterContext().getStep() == Step.AUTH;
    }

}
