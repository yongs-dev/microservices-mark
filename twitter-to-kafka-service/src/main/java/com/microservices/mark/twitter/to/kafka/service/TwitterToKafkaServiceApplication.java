package com.microservices.mark.twitter.to.kafka.service;

import com.microservices.mark.twitter.to.kafka.service.config.TwitterToKafkaServiceConfigData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("TwitterToKafkaServiceApplication started");
        log.info("{}", twitterToKafkaServiceConfigData.getTwitterKeywords());
        log.info("{}", twitterToKafkaServiceConfigData.getWelcomeMessage());
    }
}
