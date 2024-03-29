package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.converter.entry;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.converter.WithdrawalToEntryStateConverter;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.AdapterState;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.model.TransactionInfo;
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.state.deserializer.AdapterStateDeserializer;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl;
import com.rbkmoney.cds.client.storage.CdsClientStorage;
import com.rbkmoney.damsel.msgpack.Value;
import com.rbkmoney.damsel.withdrawals.provider_adapter.Withdrawal;
import com.rbkmoney.java.cds.utils.model.CardDataProxyModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

{{#if_eq doc true}}
/**
 * Конвертер запроса из Hellgate на выплату в EntryStateModel - общую модель для адаптеров.
 */
{{/if_eq}}
@Component
@RequiredArgsConstructor
public class WithdrawalToEntryConverterImpl implements WithdrawalToEntryStateConverter<EntryStateModelImpl> {

    private final CdsClientStorage cdsClientStorage;
    private final AdapterStateDeserializer adapterStateDeserializer;

    {{#if_eq doc true}}
    //todo не забудь добавить свои поля из EntryStateModelImpl
    {{/if_eq}}
    @Override
    public EntryStateModelImpl convert(Withdrawal withdrawal, Value state, Map<String, String> options) {
        EntryStateModelImpl entryModel = new EntryStateModelImpl();
        byte[] data = (state != null && state.isSetBin() && state.getBin().length > 0) ? state.getBin() : null;
        AdapterState adapterState = adapterStateDeserializer.read(data);
        TransactionInfo trxinfo = adapterState.getTrxInfo();
        if (trxinfo != null && trxinfo.getTrxExtra() != null) {
        {{#if_eq doc true}}
//            custom fields in trxExtra
//            entryModel.setPaynetOrderId(trxinfo.getTrxExtra().get(TrxExtraConstant.PAYNET_ORDER_ID));
//            entryModel.setSerialNumber(trxinfo.getTrxExtra().get(TrxExtraConstant.SERIAL_NUMBER));
        {{/if_eq}}
        }
        entryModel.setState(adapterState);
        entryModel.setWithdrawalId(withdrawal.getId());
        entryModel.setAmount(withdrawal.getBody().getAmount());
        entryModel.setCurrencyCode(withdrawal.getBody().getCurrency().getSymbolicCode());
        CardDataProxyModel cardData = cdsClientStorage.getCardData(withdrawal);
        entryModel.setPan(cardData.getPan());
        entryModel.setOptions(options);
        return entryModel;
    }
}
