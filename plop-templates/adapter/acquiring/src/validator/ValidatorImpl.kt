package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.validator

import com.rbkmoney.adapter.common.Validator
import com.rbkmoney.damsel.proxy_provider.PaymentContext
import org.springframework.stereotype.Component

{{#if_eq doc true}}
/**
 * Валидация необходимых полей в state/options. Вызывается в самом начале обработки запроса.
 */
{{/if_eq}}
@Component
class ValidatorImpl : Validator<PaymentContext> {

    override fun validate(paymentContext: PaymentContext) {
{{#if_eq doc true}}
//        пример проверок:
//        val options = paymentContext.options;
//        Objects.requireNonNull(options[OptionsField.KEY_PASS], "Option 'key_pass' must be set")
//        Objects.requireNonNull(options[OptionsField.CERT_NAME], "Option 'cert_name' must be set")
//        Objects.requireNonNull(options[OptionsField.CONTROL_KEY], "Option 'control_key' must be set")
{{/if_eq}}
    }
}
