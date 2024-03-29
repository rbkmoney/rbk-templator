package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.processor;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseResponse;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.ExitStateModelImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

{{#if_eq doc true}}
/**
 * Описание успешного процессора, должен принимать в цепочке следующий процессор.
 * Обычно цепочка состоит из двух процессоров успешный->ошибочный.
 * Здесь происходит заполнение exitStateModel на основе ответа от 3ей стороны.
 */
{{/if_eq}}
@Slf4j
@RequiredArgsConstructor
public class SuccessProcessor implements Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl> {

    private final Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl> next;

    @Override
    public ExitStateModelImpl process(BaseResponse response, EntryStateModelImpl entryStateModel) {
{{#if_eq doc true}}
//        Пример кода:
//        if (isSuccess(response)) {
//            log.info("Success response: {}", response);
//            ExitStateModelImpl exitStateModel = new ExitStateModelImpl();
//            exitStateModel.setEntryStateModel(entryStateModel);
//            if (response.getStatus() != null) {
//                exitStateModel.setStatus(response.getStatus());
//            }
//            return exitStateModel;
//        } else {
//            log.info("Received not success response: {}", response);
//            return next.process(response, entryStateModel);
//        }
{{/if_eq}}
        return null; {{#if_eq doc true}}//todo delete!{{/if_eq}}
    }
{{#if_eq doc true}}
//    public static boolean isSuccess(BaseResponse response) {
//        return response != null
//                && response.getErrorCode() == null
//                && response.getErrorMessage() == null;
//    }
{{/if_eq}}
}
