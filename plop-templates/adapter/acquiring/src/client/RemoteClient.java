package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client;

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.client.model.*;

{{#if_eq doc true}}
/**
 * Здесь находятся методы вызова 3ей стороны.
 */
{{/if_eq}}
public interface RemoteClient {

    PreAuthResponse preAuth(PreAuthRequest request);

    AuthResponse auth(AuthRequest request);

    CaptureResponse capture(CaptureRequest request);

    CancelResponse cancel(CancelRequest request);

    RecurrentResponse recurrent(RecurrentRequest request);

    StatusResponse status(StatusRequest request);

}
