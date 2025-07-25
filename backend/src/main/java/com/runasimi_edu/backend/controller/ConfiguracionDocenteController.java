package com.runasimi_edu.backend.controller;

import java.util.Optional;

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

import com.runasimi_edu.backend.dto.response.UsuarioResponse;
import com.runasimi_edu.backend.model.ConfiguracionDocente;
import com.runasimi_edu.backend.service.ConfiguracionDocenteService;
import com.runasimi_edu.backend.service.UsuarioService;

@RestController
@RequestMapping("/api/configuraciones-docentes")
@CrossOrigin(origins = "*")
public class ConfiguracionDocenteController {

    @Autowired
    private ConfiguracionDocenteService configuracionDocenteService;

    @Autowired
    private UsuarioService usuarioService;

    // Crear o actualizar configuración
    @PostMapping
    public ResponseEntity<ConfiguracionDocente> guardar(@RequestBody ConfiguracionDocente configuracion) {
        ConfiguracionDocente guardada = configuracionDocenteService.guardar(configuracion);
        return ResponseEntity.ok(guardada);
    }

    // Obtener configuración por ID
    @GetMapping("/{id}")
    public ResponseEntity<ConfiguracionDocente> obtenerPorId(@PathVariable Long id) {
        return configuracionDocenteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener configuración por ID del docente
    @GetMapping("/docente/{docenteId}")
    public ResponseEntity<ConfiguracionDocente> obtenerPorDocente(@PathVariable Long docenteId) {
        Optional<UsuarioResponse> docente = usuarioService.buscarPorId(docenteId);
        if (docente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return configuracionDocenteService.buscarPorDocente(docente.get())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Verificar si existe configuración para un docente
    @GetMapping("/existe/{docenteId}")
    public ResponseEntity<Boolean> existePorDocente(@PathVariable Long docenteId) {
        Optional<UsuarioResponse> docente = usuarioService.buscarPorId(docenteId);
        if (docente.isEmpty()) {
            return ResponseEntity.ok(false);
        }
        boolean existe = configuracionDocenteService.existePorDocente(docente.get());
        return ResponseEntity.ok(existe);
    }

    // Eliminar configuración por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            configuracionDocenteService.eliminarPorId(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
