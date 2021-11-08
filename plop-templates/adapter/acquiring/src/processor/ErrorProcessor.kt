package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.processor

import com.rbkmoney.adapter.common.model.AdapterContext
import com.rbkmoney.adapter.common.processor.Processor
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.BaseResponse
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel

{{#if_eq doc true}}
/**
 * Описание ошибочного процессора, тут мы предполагаем, что проверка на ошибку будет первой в цепочке.
 * Здесь происходит заполнение exitStateModel на основе ответа от 3ей стороны.
 */
{{/if_eq}}
class ErrorProcessor<T : BaseResponse>(
    val nextProcessor: Processor<CustomExitStateModel, T, CustomEntryStateModel>
) : Processor<CustomExitStateModel, T, CustomEntryStateModel> {

    override fun process(response: T, entryStateModel: CustomEntryStateModel): CustomExitStateModel {
        {{#if_eq doc true}}
        // Тут добавляем проверку на ошибку в ответе от 3-й стороны
        {{/if_eq}}
        val adapterContext: AdapterContext = entryStateModel.adapterContext
        if (true) {
            return CustomExitStateModel().apply {
                this.errorCode = "general-error"
                this.errorMessage = "Unknown error"
                this.adapterContext = adapterContext
                this.generalEntryStateModel = entryStateModel
                this.trxExtra = HashMap(entryStateModel.trxExtra)
            }
        } else {
            return nextProcessor.process(response, entryStateModel)
        }
    }
}
