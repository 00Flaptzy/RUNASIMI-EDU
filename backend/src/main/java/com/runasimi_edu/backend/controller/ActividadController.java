package com.runasimi_edu.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.runasimi_edu.backend.dto.request.ActividadRequest;
import com.runasimi_edu.backend.dto.response.ActividadResponse;
import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.Actividad.NivelDificultad;
import com.runasimi_edu.backend.service.ActividadService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/actividades")
@CrossOrigin(origins = "*")
public class ActividadController {

    @Autowired
    private ActividadService actividadService;

    // Listar todas las actividades
    @GetMapping
    public ResponseEntity<List<ActividadResponse>> listarTodas() {
        return ResponseEntity.ok(actividadService.listarTodas());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ActividadResponse> obtenerPorId(@PathVariable Long id) {
        try {
            ActividadResponse response = actividadService.buscarPorId(id);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear nueva actividad
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ActividadRequest request) {
        try {
            Actividad actividad = actividadService.convertirRequest(request);
            Actividad nueva = actividadService.guardar(actividad);
            ActividadResponse response = actividadService.convertirActividadResponse(nueva);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Actualizar una actividad
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody ActividadRequest request) {
        try {
            // Crear una actividad temporal con el ID para la actualización
            Actividad actividadExistente = new Actividad();
            actividadExistente.setId(id);
            
            Actividad actualizada = actividadService.actualizarActividad(actividadExistente, request);
            ActividadResponse response = actividadService.convertirActividadResponse(actualizada);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            actividadService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar por ID de grado
    @GetMapping("/grado/{gradoId}")
    public ResponseEntity<List<ActividadResponse>> buscarPorGrado(@PathVariable Long gradoId) {
        try {
            List<ActividadResponse> actividades = actividadService.buscarPorGradoId(gradoId);
            return ResponseEntity.ok(actividades);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar por ID de categoría
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ActividadResponse>> buscarPorCategoria(@PathVariable Long categoriaId) {
        try {
            List<ActividadResponse> actividades = actividadService.buscarPorCategoriaId(categoriaId);
            return ResponseEntity.ok(actividades);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar por nivel de dificultad
    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<ActividadResponse>> buscarPorNivel(@PathVariable NivelDificultad nivel) {
        try {
            List<ActividadResponse> actividades = actividadService.buscarPorNivelDificultad(nivel);
            return ResponseEntity.ok(actividades);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar por nombre (coincidencia parcial)
@GetMapping("/buscar")
public ResponseEntity<List<ActividadResponse>> buscar(
    @RequestParam(required = false) String nombre,
    @RequestParam(required = false) Long id) {
    
    if (nombre != null) {
        // Búsqueda por nombre
        List<ActividadResponse> actividades = actividadService.buscarPorNombre(nombre);
        return ResponseEntity.ok(actividades);
    } else if (id != null) {
        // Búsqueda por ID
        ActividadResponse actividad = actividadService.buscarPorId(id);
        return ResponseEntity.ok(List.of(actividad)); // Devuelve como lista
    } else {
        // Ningún parámetro proporcionado
        return ResponseEntity.badRequest().body(List.of());
    }
}

    // Buscar por grado y categoría
    @GetMapping("/grado/{gradoId}/categoria/{categoriaId}")
    public ResponseEntity<List<ActividadResponse>> buscarPorGradoYCategoria(
            @PathVariable Long gradoId, 
            @PathVariable Long categoriaId) {
        List<ActividadResponse> actividades = actividadService.buscarPorGradoYCategoria(gradoId, categoriaId);
        return ResponseEntity.ok(actividades);
    }
}