package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

{{#if_eq doc true}}
/**
 * Общие свойства для всех запросов находятся здесь. Наследуй этот класс для выделение специфичных свойств запроса.
 */
{{/if_eq}}
@Data
@SuperBuilder
@NoArgsConstructor
public class BaseRequest {

}
