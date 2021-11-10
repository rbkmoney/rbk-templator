package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.processor;

import com.rbkmoney.damsel.proxy_provider.ProviderProxySrv;
import com.rbkmoney.woody.thrift.impl.http.THServiceBuilder;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

{{#if_eq doc true}}
/**
 * Endpoint.
 */
{{/if_eq}}
@RequiredArgsConstructor
@WebServlet("/adapter/{{kebabCase bank_name}}")
public class Adapter{{properCase bank_name}}Servlet extends GenericServlet {

    private final ProviderProxySrv.Iface serverHandler;
    private Servlet servlet;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servlet = new THServiceBuilder().build(ProviderProxySrv.Iface.class, serverHandler);
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        servlet.service(request, response);
    }
}
