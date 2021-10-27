package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.processor

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseResponse
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.ExitStateModelImpl

{{#if_eq doc true}}
/**
 * Описание ошибочного процессора, должен быть последним в цепочке.
 * Обычно цепочка состоит из двух процессоров успешный->ошибочный.
 * Здесь происходит заполнение exitStateModel на основе ответа от 3ей стороны.
 */
{{/if_eq}}
class ErrorProcessor : Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl> {
    override fun process(response: BaseResponse, entryStateModel: EntryStateModelImpl): ExitStateModelImpl {
{{#if_eq doc true}}
        //        Пример кода:
//        exitStateModel.setEntryStateModel(entryStateModel);
//        if (DECLINED.equals(response.getStatus())) {
//            exitStateModel.setErrorCode("declined");
//            exitStateModel.setErrorMessage("payout declined");
//        } else {
//            String errorCode = (response.getErrorCode() != null)
//                    ? response.getErrorCode()
//                    : "unknown code";
//            String errorMessage = (response.getErrorMessage() != null)
//                    ? response.getErrorMessage()
//                    : "unknown message";
//            exitStateModel.setErrorCode(errorCode);
//            exitStateModel.setErrorMessage(errorMessage);
//        }
{{/if_eq}}
        return ExitStateModelImpl()
    }
}
