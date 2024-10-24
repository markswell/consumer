package com.example.consumer.service.impl;

import com.example.consumer.model.Paciente;
import com.example.consumer.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.data.domain.PageRequest.of;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PacienteServiceImplTest {

    @InjectMocks
    private PacienteServiceImpl service;
    private List<Paciente> pacientes;
    @Mock(extraInterfaces = {JpaRepository.class})
    private PacienteRepository repository;

    @BeforeEach
    public void init() {
        this.pacientes = findAll();
        when(repository.save(any()))
                .thenAnswer(r -> {
                    var paciente = new Paciente();
                    pacientes.add(paciente);
                    return paciente;
                });
        when(repository.findAll(Mockito.any(Pageable.class)))
                .thenReturn(new PageImpl<>(pacientes, of(1, 20) , pacientes.size()));
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
        verify(repository).findByNomeContaining("paciente-1");
        assertEquals(1, paciente.size());
    }

    private List<Paciente> findAll() {
        var list = new ArrayList<Paciente>();
        for(long i = 0; i < 60; i++) {
            list.add(new Paciente(i, "paciente-%s".formatted(i), "52671622058", LocalDate.now()));
        }
        return list;
    }

}