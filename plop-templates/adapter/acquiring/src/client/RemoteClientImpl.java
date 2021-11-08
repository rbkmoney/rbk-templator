package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

{{#if_eq doc true}}
/**
 * Здесь находится реализация методов вызова 3ей стороны.
 */
{{/if_eq}}
@Slf4j
@Service
@RequiredArgsConstructor
public class RemoteClientImpl implements RemoteClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
{{#if_eq doc true}}
//    todo implement and use
//    private final ResponseConverter<BaseResponse> responseConverter;
{{/if_eq}}

    @Value("${adapter.url}")
    private String basePath;

    @Override
    public PreAuthResponse preAuth(PreAuthRequest request) {
        return null;
    }

    @Override
    public AuthResponse auth(AuthRequest request) {
        return null;
    }

    @Override
    public CaptureResponse capture(CaptureRequest request) {
        return null;
    }

    @Override
    public CancelResponse cancel(CancelRequest request) {
        return null;
    }

    @Override
    public RecurrentResponse recurrent(RecurrentRequest request) {
        return null;
    }

    @Override
    public StatusResponse status(StatusRequest request) {
        return null;
    }

}
