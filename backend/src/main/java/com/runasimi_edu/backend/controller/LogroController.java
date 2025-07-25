package com.runasimi_edu.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runasimi_edu.backend.model.Logro;
import com.runasimi_edu.backend.model.Logro.TipoLogro;
import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.service.LogroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/logros")
@RequiredArgsConstructor
public class LogroController {

    private final LogroService logroService;

    // Registrar nuevo logro
    @PostMapping
    public ResponseEntity<Logro> registrar(@RequestBody Logro logro) {
        return ResponseEntity.ok(logroService.registrar(logro));
    }

    // Listar todos los logros
    @GetMapping
    public ResponseEntity<List<Logro>> listarTodos() {
        return ResponseEntity.ok(logroService.listarTodos());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Logro> buscarPorId(@PathVariable Long id) {
        Optional<Logro> logro = logroService.buscarPorId(id);
        return logro.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // Listar logros por ID de alumno
    @GetMapping("/alumno/{alumnoId}")
    public ResponseEntity<List<Logro>> listarPorAlumno(@PathVariable Long alumnoId) {
        Usuario alumno = new Usuario();
        alumno.setId(alumnoId);
        return ResponseEntity.ok(logroService.listarPorAlumno(alumno));
    }

    // Listar logros por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Logro>> listarPorTipo(@PathVariable TipoLogro tipo) {
        return ResponseEntity.ok(logroService.listarPorTipo(tipo));
    }

    // Listar logros por alumno y tipo
    @GetMapping("/alumno/{alumnoId}/tipo/{tipo}")
    public ResponseEntity<List<Logro>> listarPorAlumnoYTipo(@PathVariable Long alumnoId, @PathVariable TipoLogro tipo) {
        return ResponseEntity.ok(logroService.listarPorAlumnoYTipo(alumnoId, tipo));
    }

    // Contar logros por alumno
    @GetMapping("/alumno/{alumnoId}/contador")
    public ResponseEntity<Integer> contarPorAlumno(@PathVariable Long alumnoId) {
        Usuario alumno = new Usuario();
        alumno.setId(alumnoId);
        return ResponseEntity.ok(logroService.contarPorAlumno(alumno));
    }

    // Actualizar logro
    @PutMapping("/{id}")
    public ResponseEntity<Logro> actualizar(@PathVariable Long id, @RequestBody Logro logro) {
        logro.setId(id);
        return ResponseEntity.ok(logroService.actualizar(logro));
    }

    // Eliminar logro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        logroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
