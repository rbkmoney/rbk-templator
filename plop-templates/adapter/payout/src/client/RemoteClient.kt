package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client

import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseResponse
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.MoneyTransferRequest
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.StatusRequest

{{#if_eq doc true}}
/**
 * Здесь находятся методы вызова 3ей стороны.
 */
{{/if_eq}}
interface RemoteClient {

    {{#if_eq doc true}}
    // предложение стандартных названий методов для вывода и проверки статуса вывода
    {{/if_eq}}
    fun moneyTransfer(request: MoneyTransferRequest): BaseResponse

    fun status(request: StatusRequest): BaseResponse
}
