package com.runasimi_edu.backend.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.SesionAlumno;
import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.service.SesionAlumnoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ")
@RequiredArgsConstructor
public class SesionAlumnoController {

    private final SesionAlumnoService sesionAlumnoService;

    // Crear o actualizar sesión
    @PostMapping
    public ResponseEntity<SesionAlumno> guardar(@RequestBody SesionAlumno sesion) {
        return ResponseEntity.ok(sesionAlumnoService.guardarSesion(sesion));
    }

    // Listar todas las sesiones
    @GetMapping
    public ResponseEntity<List<SesionAlumno>> listarTodas() {
        return ResponseEntity.ok(sesionAlumnoService.listarTodas());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<SesionAlumno> buscarPorId(@PathVariable Long id) {
        Optional<SesionAlumno> sesion = sesionAlumnoService.buscarPorId(id);
        return sesion.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        sesionAlumnoService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    // Listar por ID de alumno y si está completado
    @GetMapping("/alumno/{alumnoId}/completado/{completado}")
    public ResponseEntity<List<SesionAlumno>> listarPorAlumnoYCompletado(
            @PathVariable Long alumnoId,
            @PathVariable Boolean completado) {
        return ResponseEntity.ok(sesionAlumnoService.listarPorAlumnoYCompletado(alumnoId, completado));
    }

    // Listar por fechas
    @GetMapping("/rango")
    public ResponseEntity<List<SesionAlumno>> listarPorRangoFechas(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
        return ResponseEntity.ok(sesionAlumnoService.buscarPorRangoFechas(inicio, fin));
    }

    // Contar completadas por actividad
    @PostMapping("/completadas/actividad")
    public ResponseEntity<Integer> contarCompletadasPorActividad(@RequestBody Actividad actividad) {
        return ResponseEntity.ok(sesionAlumnoService.contarCompletadasPorActividad(actividad));
    }

    // Sumar puntos de alumno
    @GetMapping("/puntos/alumno/{alumnoId}")
    public ResponseEntity<Integer> obtenerPuntajeTotalPorAlumno(@PathVariable Long alumnoId) {
        return ResponseEntity.ok(sesionAlumnoService.obtenerPuntajeTotalPorAlumno(alumnoId));
    }

    // Listar por objeto alumno completo
    @PostMapping("/por-alumno")
    public ResponseEntity<List<SesionAlumno>> listarPorAlumno(@RequestBody Usuario alumno) {
        return ResponseEntity.ok(sesionAlumnoService.listarPorAlumno(alumno));
    }

    // Listar por objeto actividad completo
    @PostMapping("/por-actividad")
    public ResponseEntity<List<SesionAlumno>> listarPorActividad(@RequestBody Actividad actividad) {
        return ResponseEntity.ok(sesionAlumnoService.listarPorActividad(actividad));
    }
}
