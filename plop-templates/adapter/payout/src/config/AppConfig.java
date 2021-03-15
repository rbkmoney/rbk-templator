package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.config.properties.TimerProperties;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.IntentService;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.IntentServiceImpl;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.state.deserializer.AdapterStateDeserializer;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.state.serializer.AdapterStateSerializer;
import com.rbkmoney.adapter.common.mapper.SimpleObjectMapper;
import com.rbkmoney.error.mapping.ErrorMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Различные бины для конфигурации приложения
 */
@Configuration
public class AppConfig {

    @Bean
    public AdapterStateDeserializer adapterStateDeserializer(ObjectMapper objectMapper) {) {
        return new AdapterStateDeserializer(objectMapper);
    }

    @Bean
    public AdapterStateSerializer stateSerializer(ObjectMapper objectMapper) {
        return new AdapterStateSerializer(objectMapper);
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new SimpleObjectMapper().createSimpleObjectMapperFactory();
    }

    @Bean
    public IntentService intentService(ErrorMapping errorMapping, TimerProperties timerProperties) {
        return new IntentServiceImpl(errorMapping, timerProperties);
    }

}
