package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.EntryStateModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

{{#if_eq doc true}}
/**
 * Класс с отличающимися от основных свойствами EntryStateModel.
 */
{{/if_eq}}
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EntryStateModelImpl extends EntryStateModel {

}
