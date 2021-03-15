package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.converter;


import org.springframework.http.ResponseEntity;

/**
 * Родительский класс для конвертации ответов от третьей стороны
 */
public abstract class ResponseConverter<T> {

    public abstract T convert(ResponseEntity<String> response);

}
