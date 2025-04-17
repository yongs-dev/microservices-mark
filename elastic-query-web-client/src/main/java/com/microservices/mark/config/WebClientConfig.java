package com.microservices.mark.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Configuration
@LoadBalancerClient(name = "elastic-query-service", configuration = ElasticQueryWebClientConfigData.class)
public class WebClientConfig {

    private final UserConfigData userConfigData;
    private final ElasticQueryWebClientConfigData.WebClient elasticQueryWebClientConfigData;

    public WebClientConfig(UserConfigData userConfigData, ElasticQueryWebClientConfigData elasticQueryWebClientConfigData) {
        this.userConfigData = userConfigData;
        this.elasticQueryWebClientConfigData = elasticQueryWebClientConfigData.getWebClient();
    }

    @LoadBalanced
    @Bean("webClientBuilder")
    WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .filter(ExchangeFilterFunctions.basicAuthentication(userConfigData.getUsername(), userConfigData.getPassword()))
                .baseUrl(elasticQueryWebClientConfigData.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, elasticQueryWebClientConfigData.getContentType())
                .defaultHeader(HttpHeaders.ACCEPT, elasticQueryWebClientConfigData.getAcceptType())
                .clientConnector(new ReactorClientHttpConnector(getHttpClient()))
                .codecs(clientCodecConfigurer -> clientCodecConfigurer
                        .defaultCodecs()
                        .maxInMemorySize(elasticQueryWebClientConfigData.getMaxInMemorySize())
                );
    }

    private HttpClient getHttpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, elasticQueryWebClientConfigData.getConnectTimeoutMs())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(elasticQueryWebClientConfigData.getReadTimeoutMs(), TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(elasticQueryWebClientConfigData.getWriteTimeoutMs(), TimeUnit.MILLISECONDS));
                });
    }
}
