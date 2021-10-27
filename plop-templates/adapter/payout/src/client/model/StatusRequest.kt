package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model

{{#if_eq doc true}}
/**
 * Пример запроса на проверку статуса выплаты.
 */
{{/if_eq}}
data class StatusRequest(val whatever: Any = "") : BaseRequest()
