package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.config;

import com.rbkmoney.adapter.common.component.NetworkFilterComponent
import org.apache.catalina.connector.Connector
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.servlet.Filter

@Configuration
public class ServerRestConfig {

    @Value("${server.rest.port}")
    private int restPort;

    @Value("/${server.rest.endpoint}/")
    private String restEndpoint;

    @Bean
    public FilterRegistrationBean<Filter> externalPortRestrictingFilter() {
        return new NetworkFilterComponent().externalPortRestrictingFilter(restPort, restEndpoint)
    }

    @Bean
    public FilterRegistrationBean<Filter> woodyFilter() {
        return new NetworkFilterComponent().woodyFilter(restPort, restEndpoint)
    }

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tswf = new TomcatServletWebServerFactory()
        Connector connector = Connector()
        connector.setPort(restPort)
        tswf.addAdditionalTomcatConnectors(connector)
    }


}
