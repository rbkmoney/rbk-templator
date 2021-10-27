package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.constant;

import lombok.NoArgsConstructor;

{{#if_eq doc true}}
/**
 * Поля, которые кладутся в options мапу.
 */
{{/if_eq}}
@NoArgsConstructor
public class OptionsField {
{{#if_eq doc true}}
//    пример таких полей:
//    public static final String WALLET_ID = "wallet_id";
//    public static final String LOGIN = "login";
//    public static final String ENDPOINT = "endpoint";
//    public static final String KEY_PASS = "key_pass";
//    public static final String CERT_NAME = "cert_name";
//    public static final String CONTROL_KEY = "control_key";
{{/if_eq}}
}
