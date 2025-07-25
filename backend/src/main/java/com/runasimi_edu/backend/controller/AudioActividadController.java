package com.runasimi_edu.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.runasimi_edu.backend.dto.response.AudioActividadResponse;
import com.runasimi_edu.backend.model.AudioActividad;
import com.runasimi_edu.backend.service.AudioActividadService;

@RestController
@RequestMapping("/api/audio-actividades")
@CrossOrigin(origins = "*")
public class AudioActividadController {

    @Autowired
    private AudioActividadService audioActividadService;

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody AudioActividad audioActividad) {
        if (audioActividad.getOpciones() == null || audioActividad.getOpciones().isBlank()) {
            return ResponseEntity.badRequest().body("El campo 'opciones' no puede estar vacío");
        }
        
        AudioActividad guardado = audioActividadService.guardar(audioActividad);
        // Convertimos a DTO antes de devolver la respuesta
        AudioActividadResponse response = convertirAResponse(guardado);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AudioActividadResponse>> listarTodos() {
        List<AudioActividadResponse> respuesta = audioActividadService.listarTodosDTO();
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return audioActividadService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        try {
            audioActividadService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/actividad/{actividadId}")
    public ResponseEntity<List<AudioActividadResponse>> buscarPorActividadId(@PathVariable Long actividadId) {
        List<AudioActividad> audios = audioActividadService.buscarPorActividadId(actividadId);
        List<AudioActividadResponse> response = audios.stream()
            .map(this::convertirAResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/existe")
    public ResponseEntity<Boolean> existePorRutaAudio(@RequestParam String ruta) {
        boolean existe = audioActividadService.existePorRutaAudio(ruta);
        return ResponseEntity.ok(existe);
    }

    private AudioActividadResponse convertirAResponse(AudioActividad audio) {
        return new AudioActividadResponse(
            audio.getActividad() != null ? audio.getActividad().getId() : null,
            audio.getId(),
            audio.getOpciones(),
            audio.getRespuestaCorrecta(),
            audio.getRutaAudio(),
            audio.getUrlImagenOpcional()
        );
    }
}