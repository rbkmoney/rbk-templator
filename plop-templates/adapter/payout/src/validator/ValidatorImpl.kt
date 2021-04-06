package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.validator

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.validator.WithdrawalValidator
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.constant.OptionsField
import com.rbkmoney.damsel.msgpack.Value
import com.rbkmoney.damsel.withdrawals.provider_adapter.Withdrawal
import org.springframework.stereotype.Component
import java.util.Objects

/**
 * Валидация необходимых полей в Withdrawal/state/options. Вызывается в самом начале обработки выплаты.
 *
 * @see com.rbkmoney.adapter.bank.payout.spring.boot.starter.validator.WithdrawalValidator
 * @see com.rbkmoney.adapter.bank.payout.spring.boot.starter.service.PayoutAdapterService
 */
@Component
class ValidatorImpl : WithdrawalValidator {

    override fun validate(withdrawal: Withdrawal, state: Value, options: Map<String, String>) {
//        пример проверок:
        Objects.requireNonNull(options[OptionsField.WALLET_ID], "Field 'wallet_id' can't be null")
//        Objects.requireNonNull(options[OptionsField.LOGIN], "Option 'login' must be set")
//        Objects.requireNonNull(options[OptionsField.ENDPOINT], "Option 'endpoint' must be set")
//        Objects.requireNonNull(options[OptionsField.KEY_PASS], "Option 'key_pass' must be set")
//        Objects.requireNonNull(options[OptionsField.CERT_NAME], "Option 'cert_name' must be set")
//        Objects.requireNonNull(options[OptionsField.CONTROL_KEY], "Option 'control_key' must be set")
    }
}
