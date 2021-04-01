package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.converter

import org.springframework.http.ResponseEntity

/**
 * Родительский класс для конвертации ответов от третьей стороны.
 */
abstract class ResponseConverter<T> {
    abstract fun convert(response: ResponseEntity<String>): T
}
