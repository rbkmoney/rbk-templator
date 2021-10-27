package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.converter

import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseRequest

{{#if_eq doc true}}
/**
 * Родительский класс для конвертации запросов к третьей стороне.
 */
{{/if_eq}}
abstract class RequestConverter<T> {
    abstract fun convert(request: T): BaseRequest
}
