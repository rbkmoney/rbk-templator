package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.config.properties;

import com.rbkmoney.adapter.common.properties.CommonAdapterProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

{{#if_eq doc true}}
/**
 * Проперти для работы адаптера.
 */
{{/if_eq}}
@Setter
@Getter
@Configuration
@Validated
@ConfigurationProperties(prefix = "adapter")
public class Adapter{{properCase bank_name}}Properties extends CommonAdapterProperties {

}

