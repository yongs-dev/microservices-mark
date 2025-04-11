package com.microservices.mark.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ElasticQueryServiceResponseModel {

    private String id;
    private Long userId;
    private String text;
    private LocalDateTime createdAt;
}
