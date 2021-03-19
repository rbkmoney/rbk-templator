package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.converter;

import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseRequest;

/**
 * Родительский класс для конвертации запросов к третьей стороне.
 */
public abstract class RequestConverter<T> {

    public abstract BaseRequest convert(T request);

}
