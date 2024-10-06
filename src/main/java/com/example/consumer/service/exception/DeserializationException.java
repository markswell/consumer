package com.example.consumer.service.exception;

public class DeserializationException extends RuntimeException {

    public DeserializationException(String message, Throwable t) {
        super(message, t);
    }
}
