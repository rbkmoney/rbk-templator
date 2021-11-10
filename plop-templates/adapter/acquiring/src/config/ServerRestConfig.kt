package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.config

import com.rbkmoney.adapter.common.component.NetworkFilterComponent
import org.apache.catalina.connector.Connector
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.servlet.Filter

@Configuration
class ServerRestConfig(
    @Value("\${server.rest.port}") private val restPort: Int,
    @Value("/\${server.rest.endpoint}/") private val restEndpoint: String
) {

    @Bean
    fun externalPortRestrictingFilter(): FilterRegistrationBean<Filter> =
        NetworkFilterComponent().externalPortRestrictingFilter(restPort, restEndpoint)

    @Bean
    fun woodyFilter(): FilterRegistrationBean<Filter> =
        NetworkFilterComponent().woodyFilter(restPort, restEndpoint)

    @Bean
    fun servletContainer() = TomcatServletWebServerFactory().apply {
        val connector = Connector()
        connector.port = restPort
        addAdditionalTomcatConnectors(connector)
    }
}
