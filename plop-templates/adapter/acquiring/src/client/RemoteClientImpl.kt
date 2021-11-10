package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.AuthRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.AuthResponse
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CancelRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CancelResponse
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CaptureRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.CaptureResponse
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.PreAuthRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.PreAuthResponse
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.RecurrentRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.RecurrentResponse
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.StatusRequest
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.StatusResponse
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

{{#if_eq doc true}}
/**
 * Здесь находится реализация методов вызова 3ей стороны.
 */
{{/if_eq}}
@Service
class RemoteClientImpl(
    private val restTemplate: RestTemplate,
    private val objectMapper: ObjectMapper,
{{#if_eq doc true}}
//        todo implement and use
//        private val responseConverter: ResponseConverter<BaseResponse>,
{{/if_eq}}
    @Value("\${adapter.url}") private val basePath: String
) : RemoteClient {

    private val log = KotlinLogging.logger {}

    override fun preAuth(request: PreAuthRequest): PreAuthResponse {
        TODO("Not yet implemented")
    }

    override fun auth(request: AuthRequest): AuthResponse {
        TODO("Not yet implemented")
    }

    override fun capture(request: CaptureRequest): CaptureResponse {
        TODO("Not yet implemented")
    }

    override fun cancel(request: CancelRequest): CancelResponse {
        TODO("Not yet implemented")
    }

    override fun recurrent(request: RecurrentRequest): RecurrentResponse {
        TODO("Not yet implemented")
    }

    override fun status(request: StatusRequest): StatusResponse {
        TODO("Not yet implemented")
    }
}
