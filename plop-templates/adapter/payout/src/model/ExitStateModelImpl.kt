package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.ExitStateModel

/**
 * Класс с отличающимися от основных свойствами ExitStateModel.
 */
data class ExitStateModelImpl(
    val whatever: Any = ""
//    пример свойств:
//    val status: String,
//    val paynetOrderId: String,
//    val merchantOrderId: String,
//    val serialNumber: String,
//    val byRequestSn: String,
//    val approvalCode: String,
//    val processorRrn: String,
//    val orderStage: String
) : ExitStateModel()
