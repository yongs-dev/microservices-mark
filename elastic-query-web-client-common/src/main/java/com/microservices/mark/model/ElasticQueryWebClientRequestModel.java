package com.microservices.mark.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ElasticQueryWebClientRequestModel {

    private String id;

    @NotEmpty
    private String text;
}
