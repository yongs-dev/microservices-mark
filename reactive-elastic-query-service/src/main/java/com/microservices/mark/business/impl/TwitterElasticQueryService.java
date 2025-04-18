package com.microservices.mark.business.impl;

import com.microservices.mark.business.ElasticQueryService;
import com.microservices.mark.business.ReactiveElasticQueryClient;
import com.microservices.mark.index.impl.TwitterIndexModel;
import com.microservices.mark.model.ElasticQueryServiceResponseModel;
import com.microservices.mark.transformer.ElasticToResponseModelTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwitterElasticQueryService implements ElasticQueryService {

    private final ReactiveElasticQueryClient<TwitterIndexModel> reactiveElasticQueryClient;
    private final ElasticToResponseModelTransformer elasticToResponseModelTransformer;

    @Override
    public Flux<ElasticQueryServiceResponseModel> getDocumentByText(String text) {
        log.info("Querying reactive elasticsearch for text {}", text);

        return reactiveElasticQueryClient.getIndexModelByText(text)
                .map(elasticToResponseModelTransformer::getResponseModel);
    }
}
