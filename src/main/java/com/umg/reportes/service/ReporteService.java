package com.umg.reportes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umg.reportes.models.Marcaje;
import com.umg.reportes.repository.MarcajeRepository;

@Service
public class ReporteService {

    @Autowired
    private MarcajeRepository marcajeRepository;

    public List<Marcaje> obtenerEmpleadosFueraDeHorario() {
        return marcajeRepository.findMarcajesFueraDeHorario();
    }

    public List<Marcaje> obtenerEmpleadosAntesDeHoraSalida() {
        return marcajeRepository.findMarcajesAntesDeHoraSalida();
    }
}
