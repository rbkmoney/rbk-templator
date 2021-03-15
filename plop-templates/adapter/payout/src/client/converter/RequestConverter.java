package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.converter;


import org.springframework.http.ResponseEntity;

/**
 * Родительский класс для конвертации запросов к третьей стороне
 */
public abstract class RequestConverter<T> {

    public abstract BaseRequest convert(T request);

}
