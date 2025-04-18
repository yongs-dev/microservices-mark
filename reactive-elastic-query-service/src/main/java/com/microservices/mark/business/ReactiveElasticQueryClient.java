package com.microservices.mark.business;

import com.microservices.mark.index.IndexModel;
import com.microservices.mark.index.impl.TwitterIndexModel;
import reactor.core.publisher.Flux;

public interface ReactiveElasticQueryClient<T extends IndexModel> {

    Flux<TwitterIndexModel> getIndexModelByText(String text);
}
