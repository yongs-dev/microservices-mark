package com.microservices.mark.model.assembler;

import com.microservices.mark.api.ElasticDocumentController;
import com.microservices.mark.index.impl.TwitterIndexModel;
import com.microservices.mark.model.ElasticQueryServiceResponseModel;
import com.microservices.mark.transformer.ElasticToResponseModelTransformer;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ElasticQueryServiceResponseModelAssembler extends RepresentationModelAssemblerSupport<TwitterIndexModel, ElasticQueryServiceResponseModel> {

    private final ElasticToResponseModelTransformer elasticToResponseModelTransformer;

    public ElasticQueryServiceResponseModelAssembler(ElasticToResponseModelTransformer elasticToResponseModelTransformer) {
        super(ElasticDocumentController.class, ElasticQueryServiceResponseModel.class);
        this.elasticToResponseModelTransformer = elasticToResponseModelTransformer;
    }

    @Override
    public ElasticQueryServiceResponseModel toModel(TwitterIndexModel twitterIndexModel) {
        ElasticQueryServiceResponseModel responseModel = elasticToResponseModelTransformer.getResponseModel(twitterIndexModel);
        responseModel.add(
                linkTo(
                        methodOn(ElasticDocumentController.class)
                                .getDocumentById((twitterIndexModel.getId())))
                        .withSelfRel()
        );
        responseModel.add(
                linkTo(ElasticDocumentController.class)
                        .withRel("documents"));

        return responseModel;
    }

    public List<ElasticQueryServiceResponseModel> toModels(List<TwitterIndexModel> twitterIndexModels) {
        return twitterIndexModels.stream().map(this::toModel).toList();
    }
}
