package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.config

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.rbkmoney.adapter.common.handler.callback.PaymentCallbackHandler
import com.rbkmoney.adapter.common.handler.callback.RecurrentTokenCallbackHandler
import com.rbkmoney.adapter.common.model.PollingInfo
import com.rbkmoney.adapter.common.properties.CommonTimerProperties
import com.rbkmoney.adapter.common.state.deserializer.AdapterDeserializer
import com.rbkmoney.adapter.common.state.deserializer.CallbackDeserializer
import com.rbkmoney.adapter.common.state.serializer.AdapterSerializer
import com.rbkmoney.adapter.common.utils.times.ExponentialBackOffPollingService
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

{{#if_eq doc true}}
/**
 * Различные бины для конфигурации приложения.
 */
{{/if_eq}}
@Configuration
class AppConfig {

    @Bean
    @ConfigurationProperties("time.config")
    fun timerProperties(): CommonTimerProperties {
        return CommonTimerProperties()
    }

    @Bean
    fun exponentialBackOffPollingService(): ExponentialBackOffPollingService<PollingInfo> {
        return ExponentialBackOffPollingService()
    }

    @Bean
    @Primary
    fun objectMapper(): ObjectMapper = jacksonObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
        .findAndRegisterModules()

    @Bean
    fun paymentCallbackHandler(
        adapterDeserializer: AdapterDeserializer,
        adapterSerializer: AdapterSerializer,
        callbackDeserializer: CallbackDeserializer
    ): PaymentCallbackHandler {
        return PaymentCallbackHandler(adapterDeserializer, adapterSerializer, callbackDeserializer)
    }

    @Bean
    fun recurrentTokenCallbackHandler(
        adapterDeserializer: AdapterDeserializer,
        adapterSerializer: AdapterSerializer,
        callbackDeserializer: CallbackDeserializer
    ): RecurrentTokenCallbackHandler {
        return RecurrentTokenCallbackHandler(adapterDeserializer, adapterSerializer, callbackDeserializer)
    }
}
