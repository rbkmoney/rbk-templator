package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.ExitStateModel

{{#if_eq doc true}}
/**
 * Класс с отличающимися от основных свойствами ExitStateModel.
 */
{{/if_eq}}
data class ExitStateModelImpl(
    val whatever: Any = ""
{{#if_eq doc true}}
//    пример свойств:
//    val status: String,
//    val paynetOrderId: String,
//    val merchantOrderId: String,
//    val serialNumber: String,
//    val byRequestSn: String,
//    val approvalCode: String,
//    val processorRrn: String,
//    val orderStage: String
{{/if_eq}}
) : ExitStateModel()
