package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.handler;

import com.rbkmoney.adapter.common.handler.CommonHandler;
import com.rbkmoney.adapter.common.processor.Processor;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.RemoteClientException;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel;
import lombok.RequiredArgsConstructor;
import org.apache.thrift.TException;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class StepHandler<P, R> implements CommonHandler<CustomExitStateModel, CustomEntryStateModel> {

    public static final String DEFAULT_CLIENT_ERROR_CODE = "client-err";

    private final Function<P, R> requestFunction;
    private final Converter<CustomEntryStateModel, P> converter;
    private final Processor<CustomExitStateModel, R, CustomEntryStateModel> processor;

    @Override
    public CustomExitStateModel handle(CustomEntryStateModel entryStateModel) throws TException {
        try {
            P request = converter.convert(entryStateModel);
            R response = requestFunction.apply(request);

            return processor.process(response, entryStateModel);
        } catch (RemoteClientException e) {
            {{#if_eq doc true}}
            // В качестве примера мы кидаем общую ошибку DEFAULT_CLIENT_ERROR_CODE
            // Но вы можете в exception добавить коды ошибок от 3-й стороны и передавать их в exitStateModel
            {{/if_eq}}
            CustomExitStateModel customExitStateModel = new CustomExitStateModel();
            customExitStateModel.setErrorCode(DEFAULT_CLIENT_ERROR_CODE);
            customExitStateModel.setAdapterContext(entryStateModel.getAdapterContext());
            customExitStateModel.setGeneralEntryStateModel(entryStateModel);
            customExitStateModel.setTrxExtra(new HashMap<>(entryStateModel.getTrxExtra()));
            return customExitStateModel;
        }
    }
}
