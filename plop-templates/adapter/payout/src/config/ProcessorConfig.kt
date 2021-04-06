package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.config

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.processor.Processor
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseResponse
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.ExitStateModelImpl
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.processor.ErrorProcessor
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.processor.SuccessProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Конфигурация для настройки и заведения процессоров.
 * Важно заканчивать цепочку с ErrorProcessor, чтобы все необработанные случаи падали в ошибку.
 *
 * @see com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.processor.SuccessProcessor
 * @see com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.processor.ErrorProcessor
 */
@Configuration
class ProcessorConfig {

    /**
     * ErrorProcessor и SuccessProcessor не всегда достаточно для всего сценария выплат.
     * Могут потребоваться более специфичные классы - MoneyTransferSuccessProcessor и MoneyTransferErrorProcessor для
     * проведения выплаты, а также StatusSuccessProcessor и StatusErrorProcessor для проверки статуса выплаты
     */
    @Bean
    fun responseProcessorChain(): Processor<BaseResponse, EntryStateModelImpl, ExitStateModelImpl> {
        return SuccessProcessor(ErrorProcessor())
    }
}
