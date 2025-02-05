package com.myApp.libros.service;

import com.myApp.libros.entities.Libro;
import com.myApp.libros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LibroService {

    @Autowired
    LibroRepository libroRepository;

    //almacena la ruta del directorio donde se guardarán los archivos subidos.
    private final String UPLOAD_DIR = "uploads/";

    public Libro guardarLibro(Libro libro, MultipartFile file) throws IOException {
        // Guardar el archivo PDF
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            //Se escribe el archivo en la ruta especificada.
            Files.write(path, bytes);
            //Se actualiza el objeto Libro con la ruta del archivo PDF guardado.
            libro.setRutaPdf(path.toString());
        }
        // Guardar el libro en la base de datos
        return libroRepository.save(libro);
    }




}
