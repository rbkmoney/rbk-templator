package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotEmpty

{{#if_eq doc true}}
/**
 * Проперти для restTemplate.
 */
{{/if_eq}}
@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "rest-template")
data class RestTemplateProperties(
    @NotEmpty val maxTotalPooling: Int,
    @NotEmpty val defaultMaxPerRoute: Int,
    @NotEmpty val requestTimeout: Int,
    @NotEmpty val poolTimeout: Int,
    @NotEmpty val connectionTimeout: Int
)
