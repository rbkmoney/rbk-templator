package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.config.properties

import com.rbkmoney.adapter.common.properties.CommonAdapterProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

{{#if_eq doc true}}
/**
 * Проперти для работы адаптера.
 */
{{/if_eq}}
@Validated
@ConfigurationProperties(prefix = "adapter")
class Adapter{{properCase bank_name}}Properties : CommonAdapterProperties()
