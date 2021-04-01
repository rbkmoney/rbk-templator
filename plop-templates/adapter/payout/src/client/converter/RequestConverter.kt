package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.converter

import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseRequest

/**
 * Родительский класс для конвертации запросов к третьей стороне.
 */
abstract class RequestConverter<T> {
    abstract fun convert(request: T): BaseRequest
}
