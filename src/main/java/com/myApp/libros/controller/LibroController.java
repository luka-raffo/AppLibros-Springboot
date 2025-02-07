package com.myApp.libros.controller;

import com.myApp.libros.entities.Libro;
import com.myApp.libros.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;


    @GetMapping
    public List<Libro> listarLibros(){
    return libroService.listarLibros();
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestParam("titulo") String titulo,
                                            @RequestParam("autor") String autor,
                                            @RequestParam("file") MultipartFile file) throws IOException {
        Libro libro = new Libro();
        libro.setTitulo(titulo);
        libro.setAutor(autor);

        Libro libroGuardado = libroService.guardarLibro(libro, file);
        return ResponseEntity.ok(libroGuardado);
    }
}


