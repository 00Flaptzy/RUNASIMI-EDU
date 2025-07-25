package com.runasimi_edu.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runasimi_edu.backend.model.Actividad.NivelDificultad;
import com.runasimi_edu.backend.model.FraseOrdenable;
import com.runasimi_edu.backend.service.FraseOrdenableService;

@RestController
@RequestMapping("/api/frases-ordenables")
@CrossOrigin(origins = "*")
public class FraseOrdenableController {

    @Autowired
    private FraseOrdenableService fraseOrdenableService;

    // Guardar o actualizar una frase
    @PostMapping
    public ResponseEntity<FraseOrdenable> guardar(@RequestBody FraseOrdenable frase) {
        return ResponseEntity.ok(fraseOrdenableService.guardar(frase));
    }

    // Obtener una frase por ID
    @GetMapping("/{id}")
    public ResponseEntity<FraseOrdenable> obtenerPorId(@PathVariable Long id) {
        return fraseOrdenableService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una frase por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            fraseOrdenableService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Listar todas las frases
    @GetMapping
    public ResponseEntity<List<FraseOrdenable>> listarTodas() {
        return ResponseEntity.ok(fraseOrdenableService.listarTodas());
    }

    // Buscar frases por ID de actividad
    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<FraseOrdenable>> buscarPorActividadId(@PathVariable Long actividadId) {
        return ResponseEntity.ok(fraseOrdenableService.buscarPorActividadId(actividadId));
    }

    // Buscar frases por nivel de dificultad
    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<FraseOrdenable>> buscarPorNivel(@PathVariable String nivel) {
        try {
            NivelDificultad dificultad = NivelDificultad.valueOf(nivel.toUpperCase());
            return ResponseEntity.ok(fraseOrdenableService.buscarPorNivel(dificultad));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Nivel no válido
        }
    }
}
