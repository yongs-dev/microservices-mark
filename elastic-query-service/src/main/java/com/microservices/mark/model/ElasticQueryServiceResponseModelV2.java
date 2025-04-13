package com.microservices.mark.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ElasticQueryServiceResponseModelV2 extends RepresentationModel<ElasticQueryServiceResponseModelV2> {

    private Long id;
    private Long userId;
    private String text;
    private String textV2;
}
