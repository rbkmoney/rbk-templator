package com.rbkmoney.adapterxpatemerit.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties("rest-template")
public class RestTemplateProperties {

    @NotNull
    private int maxTotalPooling;

    @NotNull
    private int defaultMaxPerRoute;

    @NotNull
    private int requestTimeout;

    @NotNull
    private int poolTimeout;

    @NotNull
    private int connectionTimeout;
}
