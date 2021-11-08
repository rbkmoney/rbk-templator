package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.processor;

import com.rbkmoney.adapter.common.processor.Processor;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.BaseResponse;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

{{#if_eq doc true}}
/**
 * Описание ошибочного процессора, тут мы предполагаем, что проверка на ошибку будет первой в цепочке.
 * Здесь происходит заполнение exitStateModel на основе ответа от 3ей стороны.
 */
{{/if_eq}}
@RequiredArgsConstructor
public class ErrorProcessor<T extends BaseResponse>
        implements Processor<CustomExitStateModel, T, CustomEntryStateModel> {

    private final Processor<CustomExitStateModel, T, CustomEntryStateModel> nextProcessor;

    @Override
    public CustomExitStateModel process(T response, CustomEntryStateModel entryStateModel) {
        if (true) {
            CustomExitStateModel customExitStateModel = new CustomExitStateModel();
            customExitStateModel.setErrorCode("general-error");
            customExitStateModel.setErrorMessage("Unknown error");
            customExitStateModel.setAdapterContext(entryStateModel.getAdapterContext());
            customExitStateModel.setGeneralEntryStateModel(entryStateModel);
            customExitStateModel.setTrxExtra(new HashMap<>(entryStateModel.getTrxExtra()));

            return customExitStateModel;
        } else {
            return nextProcessor.process(response, entryStateModel);
        }
    }
}
