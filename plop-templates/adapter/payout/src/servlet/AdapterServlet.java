package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}.servlet;

import com.rbkmoney.damsel.withdrawals.provider_adapter.AdapterSrv;
import com.rbkmoney.woody.thrift.impl.http.THServiceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;

/**
 * Endpoint.
 */
@WebServlet("/adapter/{{kebabCase bank_name}}/payout")
public class AdapterServlet extends GenericServlet {

    @Autowired
    private AdapterSrv.Iface payoutAdapterServiceLogDecorator;

    private Servlet servlet;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        servlet = new THServiceBuilder().build(AdapterSrv.Iface.class, payoutAdapterServiceLogDecorator);
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        servlet.service(request, response);
    }
}
