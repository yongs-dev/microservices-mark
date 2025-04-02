package com.microservices.mark.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@RequiredArgsConstructor
public class RetryConfig {

    private final RetryConfigData retryConfigData;

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.setInitialInterval(retryConfigData.getInitialIntervalMs());
        exponentialBackOffPolicy.setMaxInterval(retryConfigData.getMaxIntervalMs());
        exponentialBackOffPolicy.setMultiplier(retryConfigData.getMultiplier());

        retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(retryConfigData.getMaxAttempts());

        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }
}
