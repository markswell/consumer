package com.example.consumer.service.exception;

public class PacienteNotFoundException extends RuntimeException {

    public PacienteNotFoundException() {
        super("Paciente não encontrado.");
    }
}
