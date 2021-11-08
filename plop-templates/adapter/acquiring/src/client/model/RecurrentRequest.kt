package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model

{{#if_eq doc true}}
/**
 * Пример запроса на рекуррентный платеж.
 */
{{/if_eq}}
data class RecurrentRequest(val whatever: Any = "") : BaseRequest()
