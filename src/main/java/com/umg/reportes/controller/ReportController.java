package com.umg.reportes.controller;

import com.itextpdf.text.DocumentException;
import com.umg.reportes.service.PdfGeneratorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            pdfGeneratorService.generarReporteUsuarios("usuarios.pdf");
            return new ResponseEntity<>("Reporte generado correctamente", HttpStatus.OK);
        } catch (FileNotFoundException | DocumentException e) {
            return new ResponseEntity<>("Error al generar el reporte", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}