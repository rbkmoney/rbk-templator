package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.processor

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseResponse
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.ExitStateModelImpl
import mu.KotlinLogging

{{#if_eq doc true}}
/**
 * Описание успешного процессора, должен принимать в цепочке следующий процессор.
 * Обычно цепочка состоит из двух процессоров успешный->ошибочный.
 * Здесь происходит заполнение exitStateModel на основе ответа от 3ей стороны.
 */
{{/if_eq}}
class SuccessProcessor(
    private val next: Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl>
) : Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl> {

    private val log = KotlinLogging.logger { }

    override fun process(response: BaseResponse, entryStateModel: EntryStateModelImpl): ExitStateModelImpl {
{{#if_eq doc true}}
//        Пример кода:
//        return if (isSuccess(response)) {
//            log.info { "Success response: $response" }
//            return ExitStateModelImpl().apply {
//                this.entryStateModel = entryStateModel
//                this.status = response.status
//            }
//        } else {
//            log.info { "Received not success response: $response" }
//            next.process(response, entryStateModel)
//        }
{{/if_eq}}
        return ExitStateModelImpl()
    }

    companion object {
        fun isSuccess(response: BaseResponse?): Boolean =
            response != null && response.errorCode == null && response.errorMessage == null
    }
}
