package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.processor

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseResponse
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.ExitStateModelImpl
import org.slf4j.LoggerFactory

/**
 * Описание успешного процессора, должен принимать в цепочке следующий процессор.
 * Обычно цепочка состоит из двух процессоров успешный->ошибочный.
 * Здесь происходит заполнение exitStateModel на основе ответа от 3ей стороны.
 */
class SuccessProcessor(
    private val next: Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl>
) : Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl> {

    override fun process(response: BaseResponse, entryStateModel: EntryStateModelImpl): ExitStateModelImpl {
//        Пример кода:
//        return if (isSuccess(response)) {
//            log.info("Success response: {}", response)
//            return ExitStateModelImpl().apply {
//                this.entryStateModel = entryStateModel
//                this.status = response.status
//            }
//        } else {
//            log.info("Received not success response: {}", response)
//            next.process(response, entryStateModel)
//        }
        return ExitStateModelImpl()
    }

    companion object {
        private val log = LoggerFactory.getLogger(SuccessProcessor::class.java)

        fun isSuccess(response: BaseResponse?): Boolean {
            return response != null
                    && response.errorCode == null
                    && response.errorMessage == null
        }
    }
}
