package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client

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

{{#if_eq doc true}}
/**
 * Здесь находятся методы вызова 3ей стороны.
 */
{{/if_eq}}
interface RemoteClient {

    fun preAuth(request: PreAuthRequest): PreAuthResponse

    fun auth(request: AuthRequest): AuthResponse

    fun capture(request: CaptureRequest): CaptureResponse

    fun cancel(request: CancelRequest): CancelResponse

    fun recurrent(request: RecurrentRequest): RecurrentResponse

    fun status(request: StatusRequest): StatusResponse
}
