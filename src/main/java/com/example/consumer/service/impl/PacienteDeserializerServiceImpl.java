package com.example.consumer.service.impl;

import com.example.consumer.model.Paciente;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.consumer.service.PacienteDeserializerService;
import com.example.consumer.service.exception.ValidaCPFException;
import com.example.consumer.service.exception.DeserializationException;

import static com.example.consumer.service.impl.util.ValidaCPF.isCpf;

@Service
public class PacienteDeserializerServiceImpl implements PacienteDeserializerService {

    @Override
    public Paciente deserilize(String json) throws DeserializationException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Paciente paciente = mapper.readValue(json, Paciente.class);
            if (!isCpf(paciente.getCpf())) {
                throw new ValidaCPFException(paciente.getCpf());
            }
            return paciente;
        } catch (JsonProcessingException e) {
            throw new DeserializationException("Error when try deserialize %s".formatted(json), e);
        } catch (ValidaCPFException e) {
            throw new DeserializationException("%s não é um cpf válido".formatted(e.getMessage()), e);
        }
    }
}
