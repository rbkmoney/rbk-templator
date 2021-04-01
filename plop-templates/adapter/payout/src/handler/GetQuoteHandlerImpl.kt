package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.handler

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.GetQuoteHandler
import com.rbkmoney.damsel.withdrawals.provider_adapter.GetQuoteParams
import com.rbkmoney.damsel.withdrawals.provider_adapter.Quote
import org.springframework.stereotype.Component

@Component
class GetQuoteHandlerImpl : GetQuoteHandler {
    override fun handle(getQuoteParams: GetQuoteParams, map: Map<String, String>): Quote {
        throw RuntimeException("Not Supported handle")
    }
}
