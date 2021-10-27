package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.validator;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.exception.ValidationException;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.validator.WithdrawalValidator;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.constant.OptionsField;
import com.rbkmoney.damsel.msgpack.Value;
import com.rbkmoney.damsel.withdrawals.provider_adapter.Withdrawal;
import org.springframework.stereotype.Component;

import java.util.Map;

{{#if_eq doc true}}
/**
 * Валидация необходимых полей в Withdrawal/state/options. Вызывается в самом начале обработки выплаты.
 *
 * @see com.rbkmoney.adapter.bank.payout.spring.boot.starter.validator.WithdrawalValidator
 * @see com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.PayoutAdapterService
 */
{{/if_eq}}
@Component
public class ValidatorImpl implements WithdrawalValidator {

    @Override
    public void validate(Withdrawal withdrawal, Value state, Map<String, String> options) throws ValidationException {
{{#if_eq doc true}}
//        пример проверок:
//        Objects.requireNonNull(options.get(OptionsField.WALLET_ID), "Field 'wallet_id' can't be null");
//        Objects.requireNonNull(options.get(OptionsField.LOGIN), "Option 'login' must be set");
//        Objects.requireNonNull(options.get(OptionsField.ENDPOINT), "Option 'endpoint' must be set");
//        Objects.requireNonNull(options.get(OptionsField.KEY_PASS), "Option 'key_pass' must be set");
//        Objects.requireNonNull(options.get(OptionsField.CERT_NAME), "Option 'cert_name' must be set");
//        Objects.requireNonNull(options.get(OptionsField.CONTROL_KEY), "Option 'control_key' must be set");
{{/if_eq}}
    }
}
