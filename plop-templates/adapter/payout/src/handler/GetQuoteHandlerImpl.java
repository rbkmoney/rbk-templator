package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.handler;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.GetQuoteHandler;
import com.rbkmoney.damsel.withdrawals.provider_adapter.GetQuoteParams;
import com.rbkmoney.damsel.withdrawals.provider_adapter.Quote;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GetQuoteHandlerImpl implements GetQuoteHandler {

    @Override
    public Quote handle(GetQuoteParams getQuoteParams, Map<String, String> map) {
        throw new RuntimeException("Not Supported handle");
    }
}
