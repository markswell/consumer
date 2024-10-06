package com.example.consumer.service.exception;

public class ValidaCPFException extends RuntimeException {
    public ValidaCPFException(String cpf) {
        super(cpf);
    }
}
