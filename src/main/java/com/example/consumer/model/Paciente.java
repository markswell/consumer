package com.example.consumer.model;

import lombok.Data;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.example.consumer.service.impl.util.LocalDateDeserializer;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paciente")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Paciente {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "consumer_seq")
    @SequenceGenerator(name="consumer_seq", sequenceName="consumer_seq",allocationSize=1)
    private Long codigo;
    private String nome;
    private String cpf;
    @JsonProperty("data_nascimento")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataNascimento;

}
