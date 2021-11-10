package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.processor;

import com.rbkmoney.adapter.common.processor.Processor;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.AuthResponse;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel;
import com.rbkmoney.adapter.common.model.AdapterContext;

import java.util.HashMap;

public class AuthProcessor implements Processor<CustomExitStateModel, AuthResponse, CustomEntryStateModel> {

    @Override
    public CustomExitStateModel process(AuthResponse response, CustomEntryStateModel entryStateModel) {
        AdapterContext adapterContext = entryStateModel.getAdapterContext();
        // adapterContext.setTrxId(response.trxId());

        CustomExitStateModel customExitStateModel = new CustomExitStateModel();
        customExitStateModel.setAdapterContext(adapterContext);
        customExitStateModel.setGeneralEntryStateModel(entryStateModel);
        customExitStateModel.setTrxExtra(new HashMap<>(entryStateModel.getTrxExtra()));

        return customExitStateModel;
    }
}
