package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.processor

import com.rbkmoney.adapter.common.model.AdapterContext
import com.rbkmoney.adapter.common.processor.Processor
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.StatusResponse
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel

class StatusProcessor : Processor<CustomExitStateModel, StatusResponse, CustomEntryStateModel> {

    override fun process(response: StatusResponse, entryStateModel: CustomEntryStateModel): CustomExitStateModel {
        val adapterContext: AdapterContext = entryStateModel.adapterContext
        // adapterContext.setTrxId(response.trxId());

        return CustomExitStateModel().apply {
            this.adapterContext = adapterContext
            this.generalEntryStateModel = entryStateModel
            this.trxExtra = HashMap(entryStateModel.trxExtra)
        }
    }
}
