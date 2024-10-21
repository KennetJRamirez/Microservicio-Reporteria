package com.umg.reportes.service;

import com.umg.reportes.models.UserEntity;
import com.umg.reportes.repository.UserRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PdfGeneratorService {

    private final UserRepository userRepository;

    public PdfGeneratorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void generarReporteUsuarios(String outputPath) throws FileNotFoundException, JRException {
        // Obtener los datos de los usuarios
        List<UserEntity> usuarios = userRepository.findAll();

        // Cargar y compilar el archivo JRXML
        File file = new File("src/main/resources/Empleados.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Crear un data source con los usuarios
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(usuarios);

        // Parámetros si es que se necesitan
        Map<String, Object> parameters = new HashMap<>();

        // Rellenar el reporte con los datos y parámetros
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Exportar a PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);
    }
}
