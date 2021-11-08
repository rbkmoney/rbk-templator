package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.handler;

import com.rbkmoney.adapter.common.enums.Step;
import com.rbkmoney.adapter.common.processor.Processor;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.RemoteClient;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.RecurrentRequest;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.RecurrentResponse;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecurrentHandler extends StepHandler<RecurrentRequest, RecurrentResponse> {

    public RecurrentHandler(RemoteClient client,
                            Converter<CustomEntryStateModel, RecurrentRequest> converter,
                            Processor<CustomExitStateModel, RecurrentResponse, CustomEntryStateModel> processor) {
        super(client::recurrent, converter, processor);
    }

    @Override
    public boolean isHandle(CustomEntryStateModel entryStateModel) {
        return entryStateModel.getAdapterContext().getStep() == Step.AUTH;
    }

}
