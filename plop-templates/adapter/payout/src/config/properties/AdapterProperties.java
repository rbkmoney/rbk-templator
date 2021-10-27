package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.config.properties;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.config.properties.AdapterProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

{{#if_eq doc true}}
/**
 * Проперти для работы адаптера (url, user-pass, certificate, etc.).
 */
{{/if_eq}}
@Setter
@Getter
@Configuration
@Validated
public class Adapter{{properCase bank_name}}PayoutProperties extends AdapterProperties {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
