package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model

{{#if_eq doc true}}
/**
 * Пример запроса холдирования средств.
 */
{{/if_eq}}
data class AuthRequest(val whatever: Any = "") : BaseRequest()
