package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.config

import com.rbkmoney.*
import com.rbkmoney.adapter.bank.spring.boot.starter.model.GeneralExitStateModel
import com.rbkmoney.adapter.common.processor.Processor
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.*
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomEntryStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.processor.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

{{#if_eq doc true}}
/**
 * Конфигурация для настройки и заведения процессоров.
 * Важно начинать цепочку с ErrorProcessor, чтобы все ошибочные случаи обрабатывались.
 */
{{/if_eq}}
@Configuration
class ProcessorConfig {

    @Bean
    fun preAuthProcessor(): Processor<CustomExitStateModel, PreAuthResponse, CustomEntryStateModel> {
        return ErrorProcessor(PreAuthProcessor())
    }

    @Bean
    fun authProcessor(): Processor<CustomExitStateModel, AuthResponse, CustomEntryStateModel> {
        return ErrorProcessor(AuthProcessor())
    }

    @Bean
    fun cancelProcessor(): Processor<CustomExitStateModel, CancelResponse, CustomEntryStateModel> {
        return ErrorProcessor(CancelProcessor())
    }

    @Bean
    fun captureProcessor(): Processor<CustomExitStateModel, CaptureResponse, CustomEntryStateModel> {
        return ErrorProcessor(CaptureProcessor())
    }

    @Bean
    fun statusProcessor(): Processor<CustomExitStateModel, StatusResponse, CustomEntryStateModel> {
        return ErrorProcessor(StatusProcessor())
    }

    @Bean
    fun recurrentProcessor(): Processor<CustomExitStateModel, RecurrentResponse, CustomEntryStateModel> {
        return ErrorProcessor(RecurrentProcessor())
    }
}
