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

import com.runasimi_edu.backend.model.PalabraIncompleta;
import com.runasimi_edu.backend.service.PalabraIncompletaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/palabras-incompletas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Ajusta según seguridad
public class PalabraIncompletaController {

    private final PalabraIncompletaService palabraIncompletaService;

    // ✅ Crear nueva palabra
    @PostMapping
    public ResponseEntity<PalabraIncompleta> crear(@RequestBody PalabraIncompleta palabra) {
        return ResponseEntity.ok(palabraIncompletaService.guardar(palabra));
    }

    // ✅ Listar todas
    @GetMapping
    public ResponseEntity<List<PalabraIncompleta>> listarTodas() {
        return ResponseEntity.ok(palabraIncompletaService.listarTodas());
    }

    // ✅ Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<PalabraIncompleta> obtenerPorId(@PathVariable Long id) {
        return palabraIncompletaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        palabraIncompletaService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Buscar por ID de actividad
    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<PalabraIncompleta>> listarPorActividadId(@PathVariable Long actividadId) {
        return ResponseEntity.ok(palabraIncompletaService.listarPorActividadId(actividadId));
    }

    // ✅ Buscar por categoría
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<PalabraIncompleta>> listarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(palabraIncompletaService.listarPorCategoria(categoria));
    }

    // ✅ Buscar por coincidencia en palabra completa
    @GetMapping("/buscar")
    public ResponseEntity<List<PalabraIncompleta>> buscarPorPalabra(@RequestParam String palabra) {
        return ResponseEntity.ok(palabraIncompletaService.buscarPorPalabra(palabra));
    }

    // ✅ Actualizar palabra
    @PutMapping("/{id}")
    public ResponseEntity<PalabraIncompleta> actualizar(@PathVariable Long id, @RequestBody PalabraIncompleta palabraActualizada) {
        try {
            PalabraIncompleta actualizada = palabraIncompletaService.actualizar(id, palabraActualizada);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
