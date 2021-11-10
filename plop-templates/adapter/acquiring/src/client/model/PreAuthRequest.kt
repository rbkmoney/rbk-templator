package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model

{{#if_eq doc true}}
/**
 * Пример запроса на hold.
 */
{{/if_eq}}
data class PreAuthRequest(val whatever: Any = "") : BaseRequest()
