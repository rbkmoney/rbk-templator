package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model

/**
 * Общие свойства для всех ответов находятся здесь. Наследуй этот класс для выделение специфичных свойств ответов.
 */
abstract class BaseResponse(
    val errorCode: Int? = null,
    val errorMessage: String? = null
)
