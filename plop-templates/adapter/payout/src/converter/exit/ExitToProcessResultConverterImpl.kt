package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.converter.exit

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.converter.ExitStateToProcessResultConverter
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.AdapterState
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.EntryStateModel
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.ExitStateModel
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.IntentService
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.state.serializer.AdapterStateSerializer
import com.rbkmoney.adapter.common.model.PollingInfo
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.ExitStateModelImpl
import com.rbkmoney.damsel.msgpack.Value
import com.rbkmoney.damsel.withdrawals.provider_adapter.Intent
import com.rbkmoney.damsel.withdrawals.provider_adapter.ProcessResult
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class ExitToProcessResultConverterImpl(
    private val stateSerializer: AdapterStateSerializer,
    private val intentService: IntentService
) : ExitStateToProcessResultConverter<ExitStateModelImpl> {

    private val log = KotlinLogging.logger { }

    override fun convert(exitStateModel: ExitStateModelImpl): ProcessResult {
        log.info { "ExitState converter. Exit state model: $exitStateModel" }
        val entryStateModel: EntryStateModel = exitStateModel.entryStateModel
        val step = entryStateModel.state.step
        var intent = Intent()
        if (exitStateModel.nextState.pollingInfo == null) {
            initPollingInfo(exitStateModel)
        }
        val nextState: AdapterState = exitStateModel.nextState

// Тут switch() с разными статусами Step
//       пример №1
//        intent = if (step == Step.PAYOUT || step == Step.CHECK) {
//                when (exitStateModel.state) {
//                    SUCCESS -> intentService.getSuccess(exitStateModel)
//                    PENDING -> intentService.getSleep(exitStateModel)
//                    ERROR, TIMEOUT -> intentService.getFailureByCode(exitStateModel)
//                    else -> throw IllegalStateException("Payout step. Unknown payout state: ${entryStateModel.state}")
//                }
//            } else {
//                throw IllegalStateException("Unknown step: $step")
//            }

//       пример №2 (C заполнением TrxInfo)
//        val trxInfo = TransactionInfo()
//        trxInfo.setTrxId(exitStateModel.getMerchantOrderId())
//        val extra: Map<String, String> = HashMap()
//        if (exitStateModel.getPaynetOrderId() != null) {
//            extra.put(TrxExtraConstant.PAYNET_ORDER_ID, exitStateModel.getPaynetOrderId())
//        }
//        if (exitStateModel.getSerialNumber() != null) {
//            extra.put(TrxExtraConstant.SERIAL_NUMBER, exitStateModel.getSerialNumber())
//        }
//        trxInfo.setTrxExtra(extra)
//        nextState.trxInfo = trxInfo
//        when (step) {
//            Step.PAYOUT -> intent = intentService.getSleep(exitStateModel)
//            Step.CHECK -> if (exitStateModel.getStatus() != null && APPROVED.equals(exitStateModel.getStatus())) {
//                intent = Intent.finish(FinishIntent(FinishStatus.success(Success())))
//            } else {
//                intent = createSleepIntent(exitStateModel)
//            }
//            else -> throw UnsupportedOperationException("Unknown step : $step")
//        }
        val processResult = ProcessResult().apply {
            setNextState(Value.bin(stateSerializer.writeByte(nextState)))
            setIntent(intent)
        }
        log.info { "ExitState converter. ProcessResult: $processResult" }
        return processResult
    }

    private fun initPollingInfo(exitStateModel: ExitStateModel) {
        val pollingInfo = PollingInfo().apply {
            startDateTimePolling = Instant.now()
            maxDateTimePolling = intentService.extractMaxDateTimeInstant(exitStateModel.entryStateModel)
        }
        exitStateModel.nextState.pollingInfo = pollingInfo
    }
}
