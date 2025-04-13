package com.microservices.mark.transformer;

import com.microservices.mark.index.impl.TwitterIndexModel;
import com.microservices.mark.kafka.avro.model.TwitterAvroModel;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AvroToElasticModelTransformer {

    public List<TwitterIndexModel> getElasticModels(List<TwitterAvroModel> avroModels) {
        return avroModels.stream()
                .map(avroModel -> TwitterIndexModel
                        .builder()
                        .id(String.valueOf(avroModel.getId()))
                        .userId(avroModel.getUserId())
                        .text(avroModel.getText())
                        .createdAt(ZonedDateTime.ofInstant(Instant.ofEpochMilli(avroModel.getCreatedAt()), ZoneId.systemDefault()))
                        .build()
                ).toList();
    }
}
