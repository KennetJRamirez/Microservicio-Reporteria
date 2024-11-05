package com.umg.reportes.models;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHorario;

    @Column(nullable = false)
    private Time horaEntrada;

    @Column(nullable = false)
    private Time horaSalida;

    @Column(nullable = false)
    private int toleranciaEntrada;

    @Column(nullable = false)
    private int toleranciaSalida;

    // Getters y Setters
}