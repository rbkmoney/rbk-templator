package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.converter.ResponseConverter
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseResponse
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.MoneyTransferRequest
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.StatusRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

/**
 * Здесь находится реализация методов вызова 3ей стороны.
 */
@Service
class RemoteClientImpl(
        private val restTemplate: RestTemplate,
        private val objectMapper: ObjectMapper,
//        todo implement and use
//        private val responseConverter: ResponseConverter<BaseResponse>,
        @Value("\${adapter.url}") private val basePath: String
) : RemoteClient {

    override fun moneyTransfer(request: MoneyTransferRequest): BaseResponse {
        TODO("Not yet implemented")
    }

    override fun status(request: StatusRequest): BaseResponse {
        TODO("Not yet implemented")
    }

    companion object {
        private val log = LoggerFactory.getLogger(RemoteClientImpl::class.java)
    }
}
