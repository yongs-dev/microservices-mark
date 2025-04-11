package com.microservices.mark.exception;

public class ElasticQueryClientException extends RuntimeException {

    public ElasticQueryClientException() {
    }

    public ElasticQueryClientException(String message) {
        super(message);
    }

    public ElasticQueryClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
