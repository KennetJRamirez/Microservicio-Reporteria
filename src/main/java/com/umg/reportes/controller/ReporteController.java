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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> empleadosFueraDeHorario() {
        try {
            List<Marcaje> marcajes = reporteService.obtenerEmpleadosFueraDeHorario();
            return ResponseEntity.ok(marcajes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("codigo", 1, "mensaje", "Consulte con el administrador"));
        }
    }

    @GetMapping("/pdf-fuera-horario")
    public ResponseEntity<?> generarPdfFueraDeHorario(
            @RequestParam(defaultValue = "false") boolean descarga,
            HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            String disposition = descarga ? "attachment" : "inline";
            response.setHeader("Content-Disposition", disposition + "; filename=empleados_fuera_horario.pdf");

            List<Marcaje> marcajes = reporteService.obtenerEmpleadosFueraDeHorario();
            InputStream jasperStream = this.getClass().getResourceAsStream("/empleados_fuera_horario.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(marcajes);
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            exporter.exportReport();

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("codigo", 1, "mensaje", "Consulte con el administrador"));
        }
    }

    @GetMapping("/antes-hora-salida")
    public ResponseEntity<?> empleadosAntesDeHoraSalida() {
        try {
            List<Marcaje> marcajes = reporteService.obtenerEmpleadosAntesDeHoraSalida();
            return ResponseEntity.ok(marcajes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("codigo", 1, "mensaje", "Consulte con el administrador"));
        }
    }

    @GetMapping("/pdf-antes-hora-salida")
    public ResponseEntity<?> generarPdfAntesDeHoraSalida(
            @RequestParam(defaultValue = "false") boolean descarga,
            HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            String disposition = descarga ? "attachment" : "inline";
            response.setHeader("Content-Disposition", disposition + "; filename=empleados_antes_hora_salida.pdf");

            List<Marcaje> marcajes = reporteService.obtenerEmpleadosAntesDeHoraSalida();
            InputStream jasperStream = this.getClass().getResourceAsStream("/empleados_antes_hora_salida.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(marcajes);
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            exporter.exportReport();

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("codigo", 1, "mensaje", "Consulte con el administrador"));
        }
    }

    @GetMapping("/marcajeGeneral")
    public ResponseEntity<?> generarReporteGeneral(
            @RequestParam(defaultValue = "false") boolean descarga,
            HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            String disposition = descarga ? "attachment" : "inline";
            response.setHeader("Content-Disposition", disposition + "; filename=marcaje_general.pdf");

            List<Marcaje> marcajesGenenal = reporteService.marcajeGeneral();
            InputStream jasperStream = this.getClass().getResourceAsStream("/reporteGeneral.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(marcajesGenenal);
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            exporter.exportReport();

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("codigo", 1, "mensaje", "Consulte con el administrador"));
        }
    }

    @PostMapping("/departamento")
    public ResponseEntity<?> generarReportePorDepartamento(
            @RequestBody Map<String, Integer> request,
            @RequestParam(defaultValue = "false") boolean descarga,
            HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            String disposition = descarga ? "attachment" : "inline";
            response.setHeader("Content-Disposition", disposition + "; filename=marcaje_departamento.pdf");

            Integer departamentoId = request.get("departamentoId");
            List<Marcaje> marcajes = reporteService.marcajeXdepartamento(departamentoId);
            InputStream jasperStream = this.getClass().getResourceAsStream("/reporteXdepartamento.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(marcajes);
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            exporter.exportReport();

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("codigo", 1, "mensaje", "Consulte con el administrador"));
        }
    }

    @PostMapping("/individual")
    public ResponseEntity<?> generarReporteIndividual(
            @RequestBody Map<String, Long> request,
            @RequestParam(defaultValue = "false") boolean descarga,
            HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            String disposition = descarga ? "attachment" : "inline";
            response.setHeader("Content-Disposition", disposition + "; filename=marcaje_individual.pdf");

            Long usuarioId = request.get("usuarioId");
            List<Marcaje> marcajes = reporteService.marcajeXusuario(usuarioId);
            InputStream jasperStream = this.getClass().getResourceAsStream("/reporteXusuario.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(marcajes);
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            exporter.exportReport();

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("codigo", 1, "mensaje", "Consulte con el administrador"));
        }
    }
}
