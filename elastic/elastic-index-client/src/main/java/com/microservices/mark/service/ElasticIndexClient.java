package com.microservices.mark.service;

import com.microservices.mark.index.IndexModel;

import java.util.List;

public interface ElasticIndexClient<T extends IndexModel> {

    List<String> save(List<T> documents);
}
