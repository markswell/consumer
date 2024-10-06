package com.example.consumer.service.impl;

import lombok.RequiredArgsConstructor;
import com.example.consumer.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.example.consumer.service.PacienteService;
import com.example.consumer.repository.PacienteRepository;
import com.example.consumer.service.exception.PacienteNotFoundException;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository repository;

    @Override
    public void save(Paciente paciente) {
        repository.save(paciente);
    }

    @Override
    public Page<Paciente> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Paciente findById(Long id) {
        var paciente = repository.findById(id);
        return paciente.orElseThrow(PacienteNotFoundException::new);
    }
}
