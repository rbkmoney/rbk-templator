package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.processor;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.AdapterState;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.Step;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseResponse;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.ExitStateModelImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Описание ошибочного процессора, должен быть последним в цепочке.
 * Обычно цепочка состоит из двух процессоров успешный->ошибочный.
 * Здесь происходит заполнение exitStateModel на основе ответа от 3ей стороны. Определяется следующий Step для
 * процесса выплаты.
 */
@Slf4j
@RequiredArgsConstructor
public class ErrorProcessor implements Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl> {

    @Override
    public ExitStateModelImpl process(BaseResponse response, EntryStateModelImpl entryStateModel) {
        ExitStateModelImpl exitStateModel = new ExitStateModelImpl();
//        Пример кода:
//        exitStateModel.setEntryStateModel(entryStateModel);
//        exitStateModel.setNextState(AdapterState.builder().step(Step.CHECK).build());
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
        return exitStateModel;
    }
}
