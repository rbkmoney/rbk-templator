package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

{{#if_eq doc true}}
/**
 * Пример запроса на проверку статуса платежа.
 */
{{/if_eq}}
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StatusRequest extends BaseRequest {

}
