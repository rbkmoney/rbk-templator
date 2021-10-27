package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.config;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.converter.WithdrawalToEntryStateConverter;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.flow.StepResolver;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.CommonHandler;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.GetQuoteHandler;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.HandleCallbackHandler;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.PayoutAdapterService;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.PayoutAdapterServiceLogDecorator;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.validator.WithdrawalValidator;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.converter.exit.ExitToProcessResultConverterImpl;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.ExitStateModelImpl;
import com.rbkmoney.damsel.withdrawals.provider_adapter.AdapterSrv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

{{#if_eq doc true}}
/**
 * Основная конфигурация для работы с библиотекой адаптеров.
 */
{{/if_eq}}
@Slf4j
@Configuration
public class ServiceConfig {

    @Bean
    public AdapterSrv.Iface payoutAdapterService(
            WithdrawalToEntryStateConverter<EntryStateModelImpl> withdrawalToEntryStateConverter,
            ExitToProcessResultConverterImpl exitStateToProcessResultConverter,
            List<CommonHandler<EntryStateModelImpl, ExitStateModelImpl>> handlers,
            StepResolver<EntryStateModelImpl, ExitStateModelImpl> stepResolver,
            WithdrawalValidator validator,
            GetQuoteHandler getQuoteHandler,
            HandleCallbackHandler callbackResult
    ) {
        return new PayoutAdapterService<>(
                withdrawalToEntryStateConverter,
                exitStateToProcessResultConverter,
                handlers,
                stepResolver,
                validator,
                getQuoteHandler,
                callbackResult
        );
    }

    @Bean
    @Primary
    public AdapterSrv.Iface payoutAdapterServiceLogDecorator(AdapterSrv.Iface payoutAdapterService) {
        return new PayoutAdapterServiceLogDecorator(payoutAdapterService);
    }
}
