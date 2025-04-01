package com.microservices.mark.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.*;

@Slf4j
@Component
public class TwitterKafkaStatusListener extends StatusAdapter {

    @Override
    public void onStatus(Status status) {
        log.info("Twitter status with text {}", status.getText());
    }
}
