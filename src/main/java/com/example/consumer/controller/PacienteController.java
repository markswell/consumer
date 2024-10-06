package com.example.consumer.controller;

import lombok.RequiredArgsConstructor;
import com.example.consumer.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import com.example.consumer.service.PacienteService;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<Page<Paciente>> findAll(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        return ResponseEntity.ok(pacienteService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findById(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.findById(id));
    }

}
