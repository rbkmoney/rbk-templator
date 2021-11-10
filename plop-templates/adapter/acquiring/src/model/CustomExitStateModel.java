package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model;

import com.rbkmoney.adapter.bank.spring.boot.starter.model.GeneralExitStateModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CustomExitStateModel extends GeneralExitStateModel {

    private String thirdPartyId;

    private String threeDsUrl;

}
