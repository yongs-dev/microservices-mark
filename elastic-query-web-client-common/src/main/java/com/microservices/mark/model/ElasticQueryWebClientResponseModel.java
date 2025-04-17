package com.microservices.mark.model;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ElasticQueryWebClientResponseModel {

    private String id;
    private Long userId;
    private String text;
    private ZonedDateTime createdAt;
}
