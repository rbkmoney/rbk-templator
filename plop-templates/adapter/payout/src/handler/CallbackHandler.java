package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.handler;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.handler.HandleCallbackHandler;
import com.rbkmoney.damsel.msgpack.Value;
import com.rbkmoney.damsel.withdrawals.provider_adapter.Callback;
import com.rbkmoney.damsel.withdrawals.provider_adapter.CallbackResult;
import com.rbkmoney.damsel.withdrawals.provider_adapter.Withdrawal;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CallbackHandler implements HandleCallbackHandler {

    @Override
    public CallbackResult handleCallback(Callback callback, Withdrawal withdrawal, Value value,
                                         Map<String, String> map) {
        throw new RuntimeException("Not Supported handle");
    }
}
