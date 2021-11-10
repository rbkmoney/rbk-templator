package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.servlet

import com.rbkmoney.damsel.proxy_provider.ProviderProxySrv
import com.rbkmoney.woody.thrift.impl.http.THServiceBuilder
import javax.servlet.GenericServlet
import javax.servlet.Servlet
import javax.servlet.ServletConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebServlet

{{#if_eq doc true}}
/**
 * Endpoint.
 */
{{/if_eq}}
@WebServlet("/adapter/{{kebabCase bank_name}}")
class Adapter{{properCase bank_name}}Servlet(
    private val serverHandler: ProviderProxySrv.Iface,
) : GenericServlet() {

    private lateinit var servlet: Servlet

    override fun init(config: ServletConfig) {
        super.init(config)
        servlet = THServiceBuilder().build(ProviderProxySrv.Iface::class.java, serverHandler)
    }

    override fun service(request: ServletRequest, response: ServletResponse) =
        servlet.service(request, response)
}
