package com.microservices.mark.service;

import com.microservices.mark.model.ElasticQueryWebClientRequestModel;
import com.microservices.mark.model.ElasticQueryWebClientResponseModel;
import reactor.core.publisher.Flux;

public interface ElasticQueryWebClient {

    Flux<ElasticQueryWebClientResponseModel> getDataByText(ElasticQueryWebClientRequestModel request);
}
