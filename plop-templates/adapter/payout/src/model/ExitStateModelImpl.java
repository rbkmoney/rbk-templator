package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.ExitStateModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Класс с отличающимися от основных свойствами ExitStateModel.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ExitStateModelImpl extends ExitStateModel {

//    пример свойств:
//    private String status;
//    private String paynetOrderId;
//    private String merchantOrderId;
//    private String serialNumber;
//    private String byRequestSn;
//    private String approvalCode;
//    private String processorRrn;
//    private String orderStage;
}
