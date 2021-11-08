package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.constant;

import lombok.NoArgsConstructor;

{{#if_eq doc true}}
/**
 * Различные endpoint-ы 3ей стороны.
 */
{{/if_eq}}
@NoArgsConstructor
public class UrlPaths {
{{#if_eq doc true}}
//    пример таких полей:
//    public static final String MONEY_TRANSFER = "/v4/transfer-by-ref/";
//    public static final String STATUS = "/v2/status/";
{{/if_eq}}
}
