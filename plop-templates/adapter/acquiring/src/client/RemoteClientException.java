package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client;

public class RemoteClientException extends RuntimeException {
    public RemoteClientException() {
    }

    public RemoteClientException(String message) {
        super(message);
    }

    public RemoteClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteClientException(Throwable cause) {
        super(cause);
    }
}
