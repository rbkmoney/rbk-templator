package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model

{{#if_eq doc true}}
/**
 * Пример запроса на отмену/возврат платежа.
 */
{{/if_eq}}
data class CancelRequest(val whatever: Any = "") : BaseRequest()
