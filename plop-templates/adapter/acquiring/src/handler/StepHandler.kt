package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.handler

import com.rbkmoney.adapter.common.handler.CommonHandler
import com.rbkmoney.adapter.common.processor.Processor
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.RemoteClientException
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel
import org.springframework.core.convert.converter.Converter
import java.util.function.Function

abstract class StepHandler<P, R>(
    val requestFunction: Function<P, R>,
    val converter: Converter<CustomEntryStateModel, P>,
    val processor: Processor<CustomExitStateModel, R, CustomEntryStateModel>
) : CommonHandler<CustomExitStateModel, CustomEntryStateModel> {

    override fun handle(entryStateModel: CustomEntryStateModel): CustomExitStateModel {
        return try {
            val request = converter.convert(entryStateModel)!!
            val response = requestFunction.apply(request)
            processor.process(response, entryStateModel)
        } catch (e: RemoteClientException) {
            CustomExitStateModel().apply {
                errorCode = DEFAULT_CLIENT_ERROR_CODE
                adapterContext = entryStateModel.adapterContext
                generalEntryStateModel = entryStateModel
                trxExtra = HashMap<String, String>(entryStateModel.trxExtra)
            }
        }
    }

    companion object {
        const val DEFAULT_CLIENT_ERROR_CODE = "client-err"
    }
}
