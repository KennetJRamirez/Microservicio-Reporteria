package com.umg.reportes.controller;

import com.umg.reportes.service.PdfGeneratorService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/api/reportes")
public class ReportController {

    private final PdfGeneratorService pdfGeneratorService;

    public ReportController(PdfGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<String> generarReporteUsuarios() {
        try {
            // Generar el reporte PDF y guardarlo en la ruta especificada
            pdfGeneratorService.generarReporteUsuarios("usuarios_jasper.pdf");
            return new ResponseEntity<>("Reporte generado correctamente", HttpStatus.OK);
        } catch (FileNotFoundException | JRException e) {
            return new ResponseEntity<>("Error al generar el reporte", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
