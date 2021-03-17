package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.converter.exit;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.converter.ExitStateToProcessResultConverter;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.AdapterState;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.EntryStateModel;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.ExitStateModel;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.Step;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.IntentService;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.state.serializer.AdapterStateSerializer;
import com.rbkmoney.adapter.common.model.PollingInfo;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.ExitStateModelImpl;
import com.rbkmoney.damsel.msgpack.Value;
import com.rbkmoney.damsel.withdrawals.provider_adapter.Intent;
import com.rbkmoney.damsel.withdrawals.provider_adapter.ProcessResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExitToProcessResultConverterImpl implements ExitStateToProcessResultConverter<ExitStateModelImpl> {

    private final AdapterStateSerializer stateSerializer;

    private final IntentService intentService;

    @Override
    public ProcessResult convert(ExitStateModelImpl exitStateModel) {
        log.info("ExitState converter. Exit state model: {}", exitStateModel);
        final EntryStateModel entryStateModel = exitStateModel.getEntryStateModel();
        final Step step = entryStateModel.getState().getStep();
        final Intent intent = null;
        if (exitStateModel.getNextState().getPollingInfo() == null) {
            initPollingInfo(exitStateModel);
        }

        AdapterState nextState = exitStateModel.getNextState();
        /**
         * Тут switch() с разными статусами Step
         */
//       пример №1
//        if (step == Step.PAYOUT || step == Step.CHECK) {
//            switch (exitStateModel.getState()) {
//                case SUCCESS:
//                    intent = intentService.getSuccess(exitStateModel);
//                    break;
//                case PENDING:
//                    intent = intentService.getSleep(exitStateModel);
//                    break;
//                case ERROR:
//                case TIMEOUT:
//                    intent = intentService.getFailureByCode(exitStateModel);
//                    break;
//                default:
//                    throw new IllegalStateException("Payout step. Unknown payout state: " + exitStateModel.getState());
//            }
//        } else {
//            throw new IllegalStateException("Unknown step: " + step);
//        }

//       пример №2 (C заполнением TrxInfo)
//        TransactionInfo trxInfo = new TransactionInfo();
//        trxInfo.setTrxId(exitStateModel.getMerchantOrderId());
//        Map<String, String> extra = new HashMap<>();
//        if (exitStateModel.getPaynetOrderId() != null) {
//            extra.put(TrxExtraConstant.PAYNET_ORDER_ID, exitStateModel.getPaynetOrderId());
//        }
//        if (exitStateModel.getSerialNumber() != null) {
//            extra.put(TrxExtraConstant.SERIAL_NUMBER, exitStateModel.getSerialNumber());
//        }
//        trxInfo.setTrxExtra(extra);
//        nextState.setTrxInfo(trxInfo);
//        switch (step) {
//            case PAYOUT:
//                intent = intentService.getSleep(exitStateModel);
//                break;
//            case CHECK:
//                if (exitStateModel.getStatus() != null && APPROVED.equals(exitStateModel.getStatus())) {
//                    intent = Intent.finish(new FinishIntent(FinishStatus.success(new Success())));
//                } else {
//                    intent = createSleepIntent(exitStateModel);
//                }
//                break;
//            default:
//                throw new UnsupportedOperationException("Unknown step : " + step);
//        }

        final ProcessResult processResult = new ProcessResult();
        processResult.setNextState(Value.bin(stateSerializer.writeByte(nextState)));
        processResult.setIntent(intent);

        log.info("ExitState converter. ProcessResult: {}", processResult);

        return processResult;
    }

    private void initPollingInfo(ExitStateModel exitStateModel) {
        PollingInfo pollingInfo = new PollingInfo();
        pollingInfo.setStartDateTimePolling(Instant.now());
        pollingInfo.setMaxDateTimePolling(intentService.extractMaxDateTimeInstant(exitStateModel.getEntryStateModel()));
        exitStateModel.getNextState().setPollingInfo(pollingInfo);
    }

}
