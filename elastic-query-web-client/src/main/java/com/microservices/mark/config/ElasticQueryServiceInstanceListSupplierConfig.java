package com.microservices.mark.config;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Configuration
public class ElasticQueryServiceInstanceListSupplierConfig implements ServiceInstanceListSupplier {

    private final ElasticQueryWebClientConfigData.WebClient webClientConfig;

    public ElasticQueryServiceInstanceListSupplierConfig(ElasticQueryWebClientConfigData elasticQueryWebClientConfigData) {
        this.webClientConfig = elasticQueryWebClientConfigData.getWebClient();
    }

    @Override
    public Flux<List<ServiceInstance>> get(Request request) {
        return ServiceInstanceListSupplier.super.get(request);
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(
                webClientConfig.getInstances().stream()
                        .map(instance -> new DefaultServiceInstance(instance.getId(), getServiceId(), instance.getHost(), instance.getPort(), false))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public String getServiceId() {
        return webClientConfig.getServiceId();
    }


}
