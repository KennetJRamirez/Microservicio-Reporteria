package com.umg.reportes.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.umg.reportes.models.UserEntity;
import com.umg.reportes.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class PdfGeneratorService {

    private final UserRepository userRepository;

    public PdfGeneratorService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void generarReporteUsuarios(String outputPath) throws FileNotFoundException, DocumentException {
        List<UserEntity> usuarios = userRepository.findAll();

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(outputPath));

        document.open();
        for (UserEntity user : usuarios) {
            document.add(new Paragraph("ID Usuario: " + user.getIdUsuario()));
            document.add(new Paragraph("Nombre: " + user.getNombre()));
            document.add(new Paragraph("Apellido: " + user.getApellido()));
            document.add(new Paragraph("Email: " + user.getEmail()));
            document.add(new Paragraph("Teléfono: " + user.getTelefono()));
            document.add(new Paragraph("Enabled: " + (user.isEnabled() ? "Sí" : "No")));
            document.add(new Paragraph("\n"));
        }
        document.close();
    }
}