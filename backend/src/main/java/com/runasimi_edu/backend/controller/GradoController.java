package com.runasimi_edu.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.runasimi_edu.backend.dto.response.GradoResponse;
import com.runasimi_edu.backend.model.Grado;
import com.runasimi_edu.backend.service.GradoService;

@RestController
@RequestMapping("/api/grados")
@CrossOrigin(origins = "*") // Puedes personalizar el origen para producción
public class GradoController {

    private final GradoService gradoService;

    public GradoController(GradoService gradoService) {
        this.gradoService = gradoService;
    }

        // 1. Listar todos los grados ordenados
        @GetMapping
    public ResponseEntity<List<GradoResponse>> listarTodos() {
        List<GradoResponse> grados = gradoService.listarGradosOrdenados();
        return ResponseEntity.ok(grados);
    }


    // 2. Obtener un grado por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Grado> obtenerPorId(@PathVariable Long id) {
        return gradoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear un nuevo grado
    @PostMapping
    public ResponseEntity<Grado> crear(@RequestBody Grado grado) {
        try {
            Grado nuevo = gradoService.crearGrado(grado);
            return ResponseEntity.ok(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 4. Actualizar un grado existente
    @PutMapping("/{id}")
    public ResponseEntity<Grado> actualizar(@PathVariable Long id, @RequestBody Grado grado) {
        try {
            Grado actualizado = gradoService.actualizarGrado(id, grado);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. Eliminar un grado por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            gradoService.eliminarGrado(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 6. Buscar grados por coincidencia de nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<Grado>> buscarPorNombre(@RequestParam String nombre) {
        List<Grado> resultados = gradoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(resultados);
    }
}
