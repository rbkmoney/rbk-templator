package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.config

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.config.properties.RestTemplateProperties
import org.apache.http.client.config.RequestConfig
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.apache.http.ssl.SSLContextBuilder
import org.springframework.boot.actuate.metrics.web.client.MetricsRestTemplateCustomizer
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.time.Duration
import javax.net.ssl.SSLContext

@Configuration
@EnableConfigurationProperties(RestTemplateProperties::class)
class RestTemplateConfig(private val properties: RestTemplateProperties) {

    @Bean
    fun poolingHttpClientConnectionManager() =
        PoolingHttpClientConnectionManager().apply {
            maxTotal = properties.maxTotalPooling
            defaultMaxPerRoute = properties.defaultMaxPerRoute
        }

    @Bean
    fun requestConfig(): RequestConfig =
        RequestConfig.custom()
            .setConnectionRequestTimeout(properties.poolTimeout)
            .setConnectTimeout(properties.connectionTimeout)
            .setSocketTimeout(properties.requestTimeout)
            .build()

    @Bean
    fun sslContext(): SSLContext =
        SSLContextBuilder().loadTrustMaterial(null) { _, _ -> true }.build()

    @Bean
    fun httpClient(
        manager: PoolingHttpClientConnectionManager,
        requestConfig: RequestConfig,
        sslContext: SSLContext
    ): CloseableHttpClient =
        HttpClients.custom()
            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
            .setSSLContext(sslContext)
            .setConnectionManager(manager)
            .setDefaultRequestConfig(requestConfig)
            .disableAutomaticRetries()
            .setConnectionManagerShared(true)
            .build()

    @Bean
    fun requestFactory(httpClient: CloseableHttpClient) =
        HttpComponentsClientHttpRequestFactory(httpClient)

    @Bean
    fun restTemplateBuilder(
        requestFactory: HttpComponentsClientHttpRequestFactory,
        metricsRestTemplateCustomizer: MetricsRestTemplateCustomizer
    ) = RestTemplateBuilder()
        .requestFactory { requestFactory }
        .additionalCustomizers(metricsRestTemplateCustomizer)

    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate {
        val restTemplate = restTemplateBuilder
            .setConnectTimeout(Duration.ofMillis(properties.connectionTimeout.toLong()))
            .setReadTimeout(Duration.ofMillis(properties.requestTimeout.toLong()))
            .build()
        setMessageConverter(restTemplate)
        return restTemplate
    }

    private fun setMessageConverter(restTemplate: RestTemplate) {
        for (converter in restTemplate.messageConverters) {
            if (converter is StringHttpMessageConverter) {
                converter.setWriteAcceptCharset(false)
            }
        }
    }
}
