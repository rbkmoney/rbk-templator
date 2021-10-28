package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.EntryStateModel

{{#if_eq doc true}}
/**
 * Класс с отличающимися от основных свойствами EntryStateModel.
 */
{{/if_eq}}
data class EntryStateModelImpl(val whatever: Any = "") : EntryStateModel()
