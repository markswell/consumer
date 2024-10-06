package com.example.consumer.service.impl;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import com.example.consumer.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import com.example.consumer.repository.PacienteRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.oracle.OracleContainer;

import java.util.Optional;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.Pageable.*;

@SpringBootTest
@Testcontainers
class PacienteServiceImplTest {

//    @Mock
//    private PacienteRepository repository;

    @Autowired
    private PacienteServiceImpl service;

//    @Autowired
//    private KafkaContainer kafkaContainer;

    @Autowired
    private OracleContainer oracleContainer;

    @BeforeEach
    public void init() {
//        when(repository.save(createPaciente(1L)))
//                .thenReturn(createPaciente(1L));
//
//        when(repository.findAll(ofSize(20)))
//                .thenReturn(createPacienteList());
//
//        when(repository.findById(1L))
//                .thenReturn(Optional.of(createPaciente(1L)));
    }

    @Test
    @DisplayName("Should run ok")
    public void shouldSave() {
        service.save(new Paciente());
    }

    @Test
    @DisplayName("Should find all")
    public void shouldFindAll() {
        var pacientes = service.findAll(ofSize(20));
        assertEquals(20, pacientes.getSize());
    }

    @Test
    @DisplayName("Should find by id")
    public void shouldFindPage() {
        var paciente = service.findById(1L);
        assertEquals(1L, paciente.getCodigo());
    }

    private Paciente createPaciente(long id) {
        return new Paciente(id,"meu paciente", "111111111", LocalDate.now());
    }

    private Page<Paciente> createPacienteList() {
        var pacientes = new ArrayList<Paciente>();
        for(int i = 0; i < 20; i++) {
            pacientes.add(createPaciente(i));
        }
        return  new PageImpl<Paciente>(pacientes);
    }

}