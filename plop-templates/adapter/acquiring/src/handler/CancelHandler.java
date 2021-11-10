package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.handler;

import com.rbkmoney.adapter.common.enums.Step;
import com.rbkmoney.adapter.common.processor.Processor;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.RemoteClient;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CancelRequest;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CancelResponse;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CancelHandler extends StepHandler<CancelRequest, CancelResponse> {

    public CancelHandler(RemoteClient client,
                         Converter<CustomEntryStateModel, CancelRequest> converter,
                         Processor<CustomExitStateModel, CancelResponse, CustomEntryStateModel> processor) {
        super(client::cancel, converter, processor);
    }

    @Override
    public boolean isHandle(CustomEntryStateModel entryStateModel) {
        return entryStateModel.getAdapterContext().getStep() == Step.CANCEL
                || entryStateModel.getAdapterContext().getStep() == Step.REFUND;
    }

}
