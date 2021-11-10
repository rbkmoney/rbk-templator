package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.processor

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CaptureResponse
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel
import com.rbkmoney.adapter.common.model.AdapterContext
import com.rbkmoney.adapter.common.processor.Processor

class CaptureProcessor : Processor<CustomExitStateModel, CaptureResponse, CustomEntryStateModel> {

    override fun process(response: CaptureResponse, entryStateModel: CustomEntryStateModel): CustomExitStateModel {
        val adapterContext: AdapterContext = entryStateModel.adapterContext
        // adapterContext.setTrxId(response.trxId());

        return CustomExitStateModel().apply {
            this.adapterContext = adapterContext
            this.generalEntryStateModel = entryStateModel
            this.trxExtra = HashMap(entryStateModel.trxExtra)
        }
    }
}
