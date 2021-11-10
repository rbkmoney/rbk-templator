package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model

import com.rbkmoney.adapter.bank.spring.boot.starter.model.GeneralEntryStateModel

data class CustomEntryStateModel(val whatever: Any = "") : GeneralEntryStateModel()
