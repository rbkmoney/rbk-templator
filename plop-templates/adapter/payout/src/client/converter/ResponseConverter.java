package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.converter;


import org.springframework.http.ResponseEntity;

{{#if_eq doc true}}
/**
 * Родительский класс для конвертации ответов от третьей стороны.
 */
{{/if_eq}}
public abstract class ResponseConverter<T> {

    public abstract T convert(ResponseEntity<String> response);

}
