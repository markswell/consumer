package com.example.consumer.repository;

import com.example.consumer.model.Paciente;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query
    List<Paciente> findByNomeContaining(String nome);
}
