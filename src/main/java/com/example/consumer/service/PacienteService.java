package com.example.consumer.service;

import com.example.consumer.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PacienteService {

    void save(Paciente paciente);

    Page<Paciente> findAll(Pageable pageable);

    Paciente findById(Long id);
}
