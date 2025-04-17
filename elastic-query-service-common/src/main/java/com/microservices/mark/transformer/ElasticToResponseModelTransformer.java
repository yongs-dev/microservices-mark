package com.microservices.mark.transformer;

import com.microservices.mark.index.impl.TwitterIndexModel;
import com.microservices.mark.model.ElasticQueryServiceResponseModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ElasticToResponseModelTransformer {

    public ElasticQueryServiceResponseModel getResponseModel(TwitterIndexModel twitterIndexModel) {
        return ElasticQueryServiceResponseModel.builder()
                .id(twitterIndexModel.getId())
                .userId(twitterIndexModel.getUserId())
                .text(twitterIndexModel.getText())
                .createdAt(twitterIndexModel.getCreatedAt())
                .build();
    }

    public List<ElasticQueryServiceResponseModel> getResponseModels(List<TwitterIndexModel> twitterIndexModels) {
        return twitterIndexModels.stream().map(this::getResponseModel).toList();
    }
}
