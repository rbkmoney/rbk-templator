package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotEmpty

/**
 * Проперти для restTemplate.
 */
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
