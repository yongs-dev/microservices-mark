package com.microservices.mark.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.ZonedDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ElasticQueryServiceResponseModel extends RepresentationModel<ElasticQueryServiceResponseModel> {

    private String id;
    private Long userId;
    private String text;
    private ZonedDateTime createdAt;
}
