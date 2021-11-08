package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbkmoney.adapter.common.handler.callback.PaymentCallbackHandler;
import com.rbkmoney.adapter.common.handler.callback.RecurrentTokenCallbackHandler;
import com.rbkmoney.adapter.common.mapper.SimpleObjectMapper;
import com.rbkmoney.adapter.common.model.PollingInfo;
import com.rbkmoney.adapter.common.properties.CommonTimerProperties;
import com.rbkmoney.adapter.common.state.deserializer.AdapterDeserializer;
import com.rbkmoney.adapter.common.state.deserializer.CallbackDeserializer;
import com.rbkmoney.adapter.common.state.serializer.AdapterSerializer;
import com.rbkmoney.adapter.common.utils.times.ExponentialBackOffPollingService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

{{#if_eq doc true}}
/**
 * Различные бины для конфигурации приложения.
 */
{{/if_eq}}
@Configuration
public class AppConfig {

    @Bean
    @ConfigurationProperties("time.config")
    public CommonTimerProperties timerProperties() {
        return new CommonTimerProperties();
    }

    @Bean
    public ExponentialBackOffPollingService<PollingInfo> exponentialBackOffPollingService() {
        return new ExponentialBackOffPollingService<>();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new SimpleObjectMapper().createSimpleObjectMapperFactory();
    }

    @Bean
    public PaymentCallbackHandler paymentCallbackHandler(AdapterDeserializer adapterDeserializer,
                                                         AdapterSerializer adapterSerializer,
                                                         CallbackDeserializer callbackDeserializer) {
        return new PaymentCallbackHandler(adapterDeserializer, adapterSerializer, callbackDeserializer);
    }

    @Bean
    public RecurrentTokenCallbackHandler recurrentTokenCallbackHandler(AdapterDeserializer adapterDeserializer,
                                                                       AdapterSerializer adapterSerializer,
                                                                       CallbackDeserializer callbackDeserializer) {
        return new RecurrentTokenCallbackHandler(adapterDeserializer, adapterSerializer, callbackDeserializer);
    }

}
