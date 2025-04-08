package com.microservices.mark.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "elastic-config")
public class ElasticConfigData {

    private String indexName;
    private String connectionUrl;
    private Integer connectTimeoutMs;
    private Integer socketTimeoutMs;
}
