package com.example.consumer.service;

import com.example.consumer.model.Paciente;
import com.example.consumer.service.exception.DeserializationException;

public interface PacienteDeserializerService {

    Paciente deserilize(String json) throws DeserializationException;

}
