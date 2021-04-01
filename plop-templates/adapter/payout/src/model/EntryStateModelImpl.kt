package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.EntryStateModel

/**
 * Класс с отличающимися от основных свойствами EntryStateModel.
 */
data class EntryStateModelImpl(val whatever: Any = "") : EntryStateModel()
