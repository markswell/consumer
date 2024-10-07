package com.example.consumer.controller;

import lombok.RequiredArgsConstructor;
import com.example.consumer.model.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.example.consumer.service.PacienteService;
import org.springframework.data.web.PageableDefault;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping("/name")
    public ResponseEntity<List<Paciente>> findByName(@RequestParam String nome) {
        return ok(pacienteService.findByName(nome));
    }

    @GetMapping
    public ResponseEntity<Page<Paciente>> findAll(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        return ok(pacienteService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> findById(@PathVariable Long id) {
        return ok(pacienteService.findById(id));
    }

}
