package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.validator;

import com.rbkmoney.adapter.common.Validator;
import com.rbkmoney.damsel.proxy_provider.PaymentContext;
import org.springframework.stereotype.Component;

{{#if_eq doc true}}
/**
 * Валидация необходимых полей в state/options. Вызывается в самом начале обработки запроса.
 */
{{/if_eq}}
@Component
public class ValidatorImpl implements Validator<PaymentContext> {

    @Override
    public void validate(PaymentContext paymentContext) {
        {{#if_eq doc true}}
//        пример проверок:
//        Map<String, String> options = paymentContext.getOptions();
//        Objects.requireNonNull(options.get(OptionsField.KEY_PASS), "Option 'key_pass' must be set");
//        Objects.requireNonNull(options.get(OptionsField.CERT_NAME), "Option 'cert_name' must be set");
//        Objects.requireNonNull(options.get(OptionsField.CONTROL_KEY), "Option 'control_key' must be set");
        {{/if_eq}}
    }

}
