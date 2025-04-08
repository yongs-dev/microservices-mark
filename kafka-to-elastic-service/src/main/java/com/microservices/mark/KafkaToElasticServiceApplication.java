package com.microservices.mark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaToElasticServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaToElasticServiceApplication.class, args);
	}

}
