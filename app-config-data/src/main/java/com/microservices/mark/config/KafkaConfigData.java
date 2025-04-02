package com.microservices.mark.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "kafka-config")
public class KafkaConfigData {

    private String bootstrapServers;
    private String schemaRegistryUrl;
    private String schemaRegistryUrlKey;
    private String topicName;
    private List<String> topicNamesToCreate;
    private Integer numOfPartitions;
    private Short replicationFactor;
}
