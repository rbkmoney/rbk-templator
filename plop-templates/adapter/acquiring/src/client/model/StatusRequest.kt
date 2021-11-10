package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model

{{#if_eq doc true}}
/**
 * Пример запроса на проверку статуса платежа.
 */
{{/if_eq}}
data class StatusRequest(val whatever: Any = "") : BaseRequest()
