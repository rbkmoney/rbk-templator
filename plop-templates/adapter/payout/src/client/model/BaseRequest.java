package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Общие свойства для всех запросов находятся здесь. Наследуй этот класс для выделение специфичных свойств запроса.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequest {

}