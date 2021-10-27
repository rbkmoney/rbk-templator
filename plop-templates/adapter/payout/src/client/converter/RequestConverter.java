package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.converter;

import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseRequest;

{{#if_eq doc true}}
/**
 * Родительский класс для конвертации запросов к третьей стороне.
 */
{{/if_eq}}
public abstract class RequestConverter<T> {

    public abstract BaseRequest convert(T request);

}
