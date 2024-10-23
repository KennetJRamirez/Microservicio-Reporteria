package com.umg.reportes.controller;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umg.reportes.models.Marcaje;
import com.umg.reportes.service.ReporteService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/fuera-horario")
    public List<Marcaje> empleadosFueraDeHorario() {
        return reporteService.obtenerEmpleadosFueraDeHorario();
    }

    @GetMapping("/pdf-fuera-horario")
    public void generarPdfFueraDeHorario(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=empleados_fuera_horario.pdf");

        // Obtener datos
        List<Marcaje> marcajes = reporteService.obtenerEmpleadosFueraDeHorario();

        // Compilar el reporte
        InputStream jasperStream = this.getClass().getResourceAsStream("/empleados_fuera_horario.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

        // Preparar los datos para el reporte
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(marcajes);

        // Mapear parámetros si es necesario
        Map<String, Object> parameters = new HashMap<>();

        // Llenar el reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Exportar a PDF
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

        // Exportar
        exporter.exportReport();
    }

    @GetMapping("/antes-hora-salida")
    public List<Marcaje> empleadosAntesDeHoraSalida() {
        return reporteService.obtenerEmpleadosAntesDeHoraSalida();
    }
}
