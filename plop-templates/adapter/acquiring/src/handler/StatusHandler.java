package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.handler;

import com.rbkmoney.adapter.common.enums.Step;
import com.rbkmoney.adapter.common.processor.Processor;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.RemoteClient;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.StatusRequest;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.StatusResponse;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StatusHandler extends StepHandler<StatusRequest, StatusResponse> {

    public StatusHandler(RemoteClient client,
                         Converter<CustomEntryStateModel, StatusRequest> converter,
                         Processor<CustomExitStateModel, StatusResponse, CustomEntryStateModel> processor) {
        super(client::status, converter, processor);
    }

    @Override
    public boolean isHandle(CustomEntryStateModel entryStateModel) {
        return entryStateModel.getAdapterContext().getStep() == Step.CHECK_STATUS;
    }

}
