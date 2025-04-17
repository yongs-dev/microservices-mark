package com.microservices.mark.service;

import com.microservices.mark.model.ElasticQueryWebClientRequestModel;
import com.microservices.mark.model.ElasticQueryWebClientResponseModel;

import java.util.List;

public interface ElasticQueryWebclient {

    List<ElasticQueryWebClientResponseModel> getDataByText(ElasticQueryWebClientRequestModel requestModel);
}
