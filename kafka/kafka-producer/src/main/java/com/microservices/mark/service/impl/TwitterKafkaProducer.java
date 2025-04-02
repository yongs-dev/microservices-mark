package com.microservices.mark.service.impl;

import com.microservices.demo.kafka.avro.model.TwitterAvroModel;
import com.microservices.mark.service.KafkaProducer;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwitterKafkaProducer implements KafkaProducer<Long, TwitterAvroModel> {

    private final KafkaTemplate<Long, TwitterAvroModel> kafkaTemplate;

    @PreDestroy
    public void clode() {
        if (kafkaTemplate != null) {
            log.info("Closing kafka producer");
            kafkaTemplate.destroy();
        }
    }

    @Override
    public void send(String topicName, Long key, TwitterAvroModel message) {
        log.info("Sending message: '{}' to topic: '{}'", message, topicName);
        CompletableFuture<SendResult<Long, TwitterAvroModel>> kafkaResultFuture = kafkaTemplate.send(topicName, key, message);
        kafkaResultFuture.whenComplete(getCallback(topicName, message));
    }

    private BiConsumer<SendResult<Long, TwitterAvroModel>, Throwable> getCallback(String topicName, TwitterAvroModel message) {
        return (result, ex) -> {
            if (ex == null) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received new metadata. Topic: {}; Partition: {}; Offset: {}; Timestamp: {}, at time {}",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime()
                );
            } else {
                log.error("Error while sending message {} to topic {}", message.toString(), topicName, ex);
            }
        };
    }
}
