package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model;

import com.rbkmoney.adapter.bank.spring.boot.starter.model.GeneralEntryStateModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CustomEntryStateModel extends GeneralEntryStateModel {

}
