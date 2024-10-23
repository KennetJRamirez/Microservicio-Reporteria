package com.umg.reportes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.umg.reportes.models.Marcaje;

@Repository
public interface MarcajeRepository extends JpaRepository<Marcaje, Integer> {

    // Para encontrar registros fuera del horario de entrada
    @Query("SELECT m FROM Marcaje m WHERE m.dentroDeHorario = false AND m.horaEntrada > m.usuario.horario.horaEntrada")
    List<Marcaje> findMarcajesFueraDeHorario();

    // Para encontrar empleados que marcaron antes de la hora de salida
    @Query("SELECT m FROM Marcaje m WHERE m.horaSalida < m.usuario.horario.horaSalida")
    List<Marcaje> findMarcajesAntesDeHoraSalida();
}