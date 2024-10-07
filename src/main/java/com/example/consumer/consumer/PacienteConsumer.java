package com.example.consumer.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.example.consumer.service.PacienteService;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import com.example.consumer.service.PacienteDeserializerService;

@Component
@RequiredArgsConstructor
public class PacienteConsumer {

    private final PacienteService pacienteService;
    private final PacienteDeserializerService pacienteDeserializerService;

    @KafkaListener(topics = "paciente", groupId = "consumer_group")
    @RetryableTopic(attempts = "1", kafkaTemplate = "kafkaTemplate")
    public void consume(String json) {
        var paciente = pacienteDeserializerService.deserilize(json);
        pacienteService.save(paciente);
    }

    @DltHandler
    public void handleDltPayment(String message) {
        System.out.println("error on message %s".formatted(message));
    }


}
