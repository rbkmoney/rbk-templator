package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model

{{#if_eq doc true}}
/**
 * Общие свойства для всех ответов находятся здесь. Наследуй этот класс для выделение специфичных свойств ответов.
 */
{{/if_eq}}
abstract class BaseResponse(
    val errorCode: Int? = null,
    val errorMessage: String? = null
)
