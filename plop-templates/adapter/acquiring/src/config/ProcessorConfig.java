package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.config;

import com.rbkmoney.adapter.common.processor.Processor;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.*;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.processor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

{{#if_eq doc true}}
/**
 * Конфигурация для настройки и заведения процессоров.
 * Важно начинать цепочку с ErrorProcessor, чтобы все ошибочные случаи обрабатывались.
 */
{{/if_eq}}
@Configuration
public class ProcessorConfig {

    @Bean
    public Processor<CustomExitStateModel, PreAuthResponse, CustomEntryStateModel> preAuthProcessor() {
        return new ErrorProcessor<>(new PreAuthProcessor());
    }

    @Bean
    public Processor<CustomExitStateModel, AuthResponse, CustomEntryStateModel> authProcessor() {
        return new ErrorProcessor<>(new AuthProcessor());
    }

    @Bean
    public Processor<CustomExitStateModel, CancelResponse, CustomEntryStateModel> cancelProcessor() {
        return new ErrorProcessor<>(new CancelProcessor());
    }

    @Bean
    public Processor<CustomExitStateModel, CaptureResponse, CustomEntryStateModel> captureProcessor() {
        return new ErrorProcessor<>(new CaptureProcessor());
    }

    @Bean
    public Processor<CustomExitStateModel, StatusResponse, CustomEntryStateModel> statusProcessor() {
        return new ErrorProcessor<>(new StatusProcessor());
    }

    @Bean
    public Processor<CustomExitStateModel, RecurrentResponse, CustomEntryStateModel> recurrentProcessor() {
        return new ErrorProcessor<>(new RecurrentProcessor());
    }

}
