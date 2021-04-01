package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.converter.entry;

import com.rbkmoney.adapter.bank.payout.spring.boot.starter.converter.WithdrawalToEntryStateConverter
import com.rbkmoney.adapter.bank.payout.spring.boot.starter.state.deserializer.AdapterStateDeserializer
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.model.EntryStateModelImpl;
import com.rbkmoney.cds.client.storage.CdsClientStorage
import com.rbkmoney.damsel.msgpack.Value
import com.rbkmoney.damsel.withdrawals.provider_adapter.Withdrawal
import org.springframework.stereotype.Component

/**
 * Конвертер запроса из Hellgate на выплату в EntryStateModel - общую модель для адаптеров.
 */
@Component
class WithdrawalToEntryConverterImpl(
    private val cdsClientStorage: CdsClientStorage,
    private val adapterStateDeserializer: AdapterStateDeserializer
): WithdrawalToEntryStateConverter<EntryStateModelImpl> {

    //todo не забудь добавить свои поля из EntryStateModelImpl
    override fun convert(withdrawal: Withdrawal, state: Value?, options: MutableMap<String, String>?): EntryStateModelImpl {
        val entryModel = EntryStateModelImpl()
        val data = state?.let {
            if (state.isSetBin && state.bin.isNotEmpty()) {
                state.bin
            } else null
        }
        val adapterState = adapterStateDeserializer.read(data)
        val trxinfo = adapterState.trxInfo
        trxinfo?.trxExtra?.let {
//            custom fields in trxExtra
//            entryModel.paynetOrderId = trxinfo.trxExtra[TrxExtraConstant.PAYNET_ORDER_ID])
//            entryModel.serialNumber = trxinfo.trxExtra[TrxExtraConstant.SERIAL_NUMBER])
        }
        entryModel.state = adapterState
        entryModel.withdrawalId = withdrawal.getId()
        entryModel.amount = withdrawal.body.amount
        entryModel.currencyCode = withdrawal.body.currency.symbolicCode

        val cardData = cdsClientStorage.getCardData(withdrawal)
        entryModel.pan = cardData.pan
        entryModel.options = options

        return entryModel
    }
}
