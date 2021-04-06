package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client

import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseResponse
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.MoneyTransferRequest
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.StatusRequest

/**
 * Здесь находятся методы вызова 3ей стороны.
 */
interface RemoteClient {

    // предложение стандартных названий методов для вывода и проверки статуса вывода
    fun moneyTransfer(request: MoneyTransferRequest): BaseResponse

    fun status(request: StatusRequest): BaseResponse
}
