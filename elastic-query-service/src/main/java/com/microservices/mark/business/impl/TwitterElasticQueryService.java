package com.microservices.mark.business.impl;

import com.microservices.mark.business.ElasticQueryService;
import com.microservices.mark.index.impl.TwitterIndexModel;
import com.microservices.mark.model.assembler.ElasticQueryServiceResponseModelAssembler;
import com.microservices.mark.service.ElasticQueryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwitterElasticQueryService implements ElasticQueryService {

    private final ElasticQueryServiceResponseModelAssembler responseModelAssembler;
    private final ElasticQueryClient<TwitterIndexModel> elasticQueryClient;
    private final ElasticQueryServiceResponseModelAssembler elasticQueryServiceResponseModelAssembler;

    @Override
    public ElasticQueryServiceResponseModel getDocumentById(String id) {
        log.info("Querying elasticsearch by id {}", id);
        return responseModelAssembler.toModel(
                elasticQueryClient.getIndexModelById(id)
        );
    }

    @Override
    public List<ElasticQueryServiceResponseModel> getDocumentsByText(String text) {
        log.info("Querying elasticsearch by text {}", text);
        return elasticQueryServiceResponseModelAssembler.toModels(
                elasticQueryClient.getIndexModelByText(text)
        );
    }

    @Override
    public List<ElasticQueryServiceResponseModel> getAllDocuments() {
        log.info("Querying all documents in elasticsearch");
        return elasticQueryServiceResponseModelAssembler.toModels(
                elasticQueryClient.getAllIndexModels()
        );
    }
}
