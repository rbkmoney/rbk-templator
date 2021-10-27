package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model

{{#if_eq doc true}}
/**
 * Пример запроса на выплату.
 */
{{/if_eq}}
data class MoneyTransferRequest(val whatever: Any = "") : BaseRequest()
