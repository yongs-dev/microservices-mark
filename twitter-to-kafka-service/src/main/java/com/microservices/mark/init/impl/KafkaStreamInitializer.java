package com.microservices.mark.init.impl;

import com.microservices.mark.client.KafkaAdminClient;
import com.microservices.mark.config.KafkaConfigData;
import com.microservices.mark.init.StreamInitializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaStreamInitializer implements StreamInitializer {

    private final KafkaConfigData kafkaConfigData;
    private final KafkaAdminClient kafkaAdminClient;

    @Override
    public void init() {
        kafkaAdminClient.createTopics();
        kafkaAdminClient.checkSchemaRegistry();
        log.info("Topics with name {} is ready for operations", kafkaConfigData.getTopicNamesToCreate().toArray());
    }
}
