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

    //[gmonzon][23102024] Obtener los datos para reporte marcaje general
    public List<Marcaje> marcajeGeneral(){
        return marcajeRepository.findAll();
    }

    //[gmonzon][23102024] Obtner los datos para reporte marcaje por departamento
    public List<Marcaje> marcajeXdepartamento(Integer departamentoId) {
        return marcajeRepository.findByUsuario_Departamento_IdDepartamento(departamentoId);
    }

    //[gmonzon][23102024] Obtener los datos para reporte marcaje individual
    public List<Marcaje> marcajeXusuario (Long usuarioId) {
        return marcajeRepository.findByUsuario_IdUsuario(usuarioId);
    }
}
