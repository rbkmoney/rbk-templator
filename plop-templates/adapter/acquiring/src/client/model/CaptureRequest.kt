package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model

{{#if_eq doc true}}
/**
 * Пример запроса на capture платежа.
 */
{{/if_eq}}
data class CaptureRequest(val whatever: Any = "") : BaseRequest()
