package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.converter.ResponseConverter;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.BaseResponse;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.MoneyTransferRequest;
import com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.client.model.StatusRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Здесь находится реализация методов вызова 3ей стороны
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RemoteClientImpl implements RemoteClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ResponseConverter<BaseResponse> responseConverter;

    @Value("${adapter.url}")
    private String basePath;

    @Override
    public BaseResponse moneyTransfer(MoneyTransferRequest request) {

    }

    @Override
    public BaseResponse status(StatusRequest request) {

    }

}
