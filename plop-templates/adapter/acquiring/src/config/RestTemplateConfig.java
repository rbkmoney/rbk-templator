package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.config;

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.config.properties.RestTemplateProperties;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.actuate.metrics.web.client.MetricsRestTemplateCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RestTemplateConfig {

    private final RestTemplateProperties restTemplateProperties;

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(restTemplateProperties.getMaxTotalPooling());
        connectionManager.setDefaultMaxPerRoute(restTemplateProperties.getDefaultMaxPerRoute());
        return connectionManager;
    }

    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(restTemplateProperties.getPoolTimeout())
                .setConnectTimeout(restTemplateProperties.getConnectionTimeout())
                .setSocketTimeout(restTemplateProperties.getRequestTimeout())
                .build();
    }

    @Bean
    public SSLContext sslContext() throws NoSuchAlgorithmException, KeyManagementException {
        return new SSLContextBuilder().build();
    }

    @Bean
    public CloseableHttpClient httpClient(
            PoolingHttpClientConnectionManager manager,
            RequestConfig requestConfig,
            SSLContext sslContext) {
        return HttpClients.custom()
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .setSSLContext(sslContext)
                .setConnectionManager(manager)
                .setDefaultRequestConfig(requestConfig)
                .disableAutomaticRetries()
                .setConnectionManagerShared(true)
                .build();
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory(CloseableHttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return requestFactory;
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder(
            HttpComponentsClientHttpRequestFactory requestFactory,
            MetricsRestTemplateCustomizer metricsRestTemplateCustomizer) {
        return new RestTemplateBuilder()
                .requestFactory(() -> requestFactory)
                .additionalCustomizers(metricsRestTemplateCustomizer);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        formHttpMessageConverter.addSupportedMediaTypes(MediaType.TEXT_HTML);
        RestTemplate restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(restTemplateProperties.getConnectionTimeout()))
                .setReadTimeout(Duration.ofMillis(restTemplateProperties.getRequestTimeout()))
                .additionalMessageConverters(formHttpMessageConverter)
                .build();

        setMessageConverter(restTemplate);
        return restTemplate;
    }

    private void setMessageConverter(RestTemplate restTemplate) {
        for (HttpMessageConverter converter : restTemplate.getMessageConverters()) {
            if (converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) converter).setWriteAcceptCharset(false);
            }
        }
    }

}
