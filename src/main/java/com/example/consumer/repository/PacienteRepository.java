package com.example.consumer.repository;

import com.example.consumer.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
