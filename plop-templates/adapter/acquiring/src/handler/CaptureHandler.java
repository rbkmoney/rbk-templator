package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.handler;

import com.rbkmoney.adapter.common.enums.Step;
import com.rbkmoney.adapter.common.processor.Processor;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.RemoteClient;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CaptureRequest;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CaptureResponse;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CaptureHandler extends StepHandler<CaptureRequest, CaptureResponse> {

    public CaptureHandler(RemoteClient client,
                          Converter<CustomEntryStateModel, CaptureRequest> converter,
                          Processor<CustomExitStateModel, CaptureResponse, CustomEntryStateModel> processor) {
        super(client::capture, converter, processor);
    }

    @Override
    public boolean isHandle(CustomEntryStateModel entryStateModel) {
        return entryStateModel.getAdapterContext().getStep() == Step.AUTH;
    }

}
