package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model;

import lombok.Getter;
import lombok.Setter;

{{#if_eq doc true}}
/**
 * Общие свойства для всех ответов находятся здесь. Наследуй этот класс для выделение специфичных свойств ответов.
 */
{{/if_eq}}
@Getter
@Setter
public class BaseResponse {

}
