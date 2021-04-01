package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.handler

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.HandleCallbackHandler
import com.rbkmoney.damsel.msgpack.Value
import com.rbkmoney.damsel.withdrawals.provider_adapter.Callback
import com.rbkmoney.damsel.withdrawals.provider_adapter.CallbackResult
import com.rbkmoney.damsel.withdrawals.provider_adapter.Withdrawal
import org.springframework.stereotype.Component

@Component
class CallbackHandler : HandleCallbackHandler {
    override fun handleCallback(
        callback: Callback,
        withdrawal: Withdrawal,
        value: Value,
        map: Map<String, String>
    ): CallbackResult {
        throw RuntimeException("Not Supported handle")
    }
}
