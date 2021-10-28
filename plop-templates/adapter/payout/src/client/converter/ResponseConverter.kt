package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.converter

import org.springframework.http.ResponseEntity

{{#if_eq doc true}}
/**
 * Родительский класс для конвертации ответов от третьей стороны.
 */
{{/if_eq}}
abstract class ResponseConverter<T> {
    abstract fun convert(response: ResponseEntity<String>): T
}
