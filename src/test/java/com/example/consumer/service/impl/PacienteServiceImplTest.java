package com.example.consumer.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.example.consumer.model.Paciente;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import com.example.consumer.service.PacienteService;
import com.example.consumer.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.PageRequest.*;

class PacienteServiceImplTest {

    private PacienteService service;
    private List<Paciente> pacientes;
    private PacienteRepository repository;

    public PacienteServiceImplTest() {
        this.pacientes = findAll();
        this.repository = mock(PacienteRepository.class);
        this.service = new PacienteServiceImpl(repository);

        when(repository.save(any()))
                .thenAnswer(r -> {
                    var paciente = new Paciente();
                    pacientes.add(paciente);
                    return paciente;
                });
        when(repository.findAll(Pageable.ofSize(20)))
                .thenReturn(new PageImpl(pacientes, of(1, 20) , pacientes.size()));
        when(repository.findById(1L))
                .thenReturn(Optional.of(pacientes.get(1)));
        when(repository.findByNomeContaining("paciente-1"))
                .thenReturn(pacientes.stream().filter(p -> p.getNome().equals("paciente-1")).toList());
    }

    @Test
    @DisplayName("Should run ok")
    public void shouldSave() {
        service.save(new Paciente());
        assertEquals(61, pacientes.size());
    }

    @Test
    @DisplayName("Should find all")
    public void shouldFindAll() {
        var pacientes = service.findAll(Pageable.ofSize(20));
        assertEquals(20, pacientes.getSize());
    }

    @Test
    @DisplayName("Should find by id")
    public void shouldFindPage() {
        var paciente = service.findById(1L);
        assertEquals(1L, paciente.getCodigo());
    }

    @Test
    @DisplayName("Should find by name")
    public void shouldFindByName() {
        List<Paciente> paciente = service.findByName("paciente-1");
        assertEquals(1, paciente.size());
    }

    private Paciente createPaciente(long id) {
        return new Paciente(id,"meu paciente", "111111111", LocalDate.now());
    }

    private List<Paciente> findAll() {
        var list = new ArrayList<Paciente>();
        for(long i = 0; i < 60; i++) {
            list.add(new Paciente(i, "paciente-%s".formatted(i), "52671622058", LocalDate.now()));
        }
        return list;
    }

}