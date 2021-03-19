package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.handler;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.CommonHandlerImpl;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.Step;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.RemoteClient;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseResponse;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.MoneyTransferRequest;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.ExitStateModelImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Описание обработчика, конвертера, процессора для шага удовлетворяющего условию в isHandle - Step.PAYOUT.
 */
@Component
public class MoneyTransferHandler
        extends CommonHandlerImpl<MoneyTransferRequest, BaseResponse, EntryStateModelImpl, ExitStateModelImpl> {

    public MoneyTransferHandler(RemoteClient remoteClient,
                                Converter<EntryStateModelImpl, MoneyTransferRequest> converter,
                                Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl>
                                        responseProcessorChain) {
        super(remoteClient::moneyTransfer, converter, responseProcessorChain);
    }

    @Override
    public boolean isHandle(EntryStateModelImpl entryStateModel) {
        return entryStateModel.getState().getStep() == Step.PAYOUT;
    }
}
