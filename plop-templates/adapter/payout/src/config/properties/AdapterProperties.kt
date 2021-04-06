package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotEmpty

/**
 * Проперти для работы адаптера (url, user-pass, certificate, etc.).
 */
@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "adapter")
data class Adapter{{properCase bank_name}}PayoutProperties(
    @NotEmpty val url: String,
    @NotEmpty val username: String,
    @NotEmpty val password: String
)
