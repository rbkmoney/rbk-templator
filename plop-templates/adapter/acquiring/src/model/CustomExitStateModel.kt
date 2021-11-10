package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model

import com.rbkmoney.adapter.bank.spring.boot.starter.model.GeneralExitStateModel

data class CustomExitStateModel(
    val thirdPartyId: String? = null,
    val threeDsUrl: String? = null,
) : GeneralExitStateModel()
