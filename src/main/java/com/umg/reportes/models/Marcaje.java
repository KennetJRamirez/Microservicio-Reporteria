package com.umg.reportes.models;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "marcaje")
public class Marcaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMarcaje;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(nullable = false)
    private Date fechaMarcaje;

    @Column(nullable = false)
    private Time horaEntrada;

    @Column(nullable = false)
    private Time horaSalida;

    @Column(nullable = false)
    private boolean dentroDeHorario;

    // Getters y Setters
}