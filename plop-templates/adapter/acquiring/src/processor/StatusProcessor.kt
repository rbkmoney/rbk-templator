package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.processor

import com.rbkmoney.adapter.bank.client.model.StatusResponse
import com.rbkmoney.adapter.bank.model.CustomEntryStateModel
import com.rbkmoney.adapter.bank.model.CustomExitStateModel
import com.rbkmoney.adapter.common.model.AdapterContext
import com.rbkmoney.adapter.common.processor.Processor

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
