package com.runasimi_edu.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runasimi_edu.backend.dto.request.UsuarioRequest;
import com.runasimi_edu.backend.dto.response.UsuarioResponse;
import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.model.Usuario.RolUsuario;
import com.runasimi_edu.backend.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<?> obtenerPorDni(@PathVariable String dni) {
        return usuarioService.buscarPorDni(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> obtenerPorEmail(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/existe/dni/{dni}")
    public ResponseEntity<Boolean> existePorDni(@PathVariable String dni) {
        return ResponseEntity.ok(usuarioService.existePorDni(dni));
    }

    @GetMapping("/existe/email/{email}")
    public ResponseEntity<Boolean> existePorEmail(@PathVariable String email) {
        return ResponseEntity.ok(usuarioService.existePorEmail(email));
    }

    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<UsuarioResponse>> listarPorRol(@PathVariable RolUsuario rol) {
        return ResponseEntity.ok(usuarioService.listarPorRol(rol));
    }

    @GetMapping("/grado/{gradoId}")
    public ResponseEntity<List<UsuarioResponse>> listarPorGrado(@PathVariable Long gradoId) {
        return ResponseEntity.ok(usuarioService.listarPorGrado(gradoId));
    }

    @GetMapping("/activo/{estado}")
    public ResponseEntity<List<Usuario>> listarPorActivo(@PathVariable Boolean estado) {
        return ResponseEntity.ok(usuarioService.listarPorActivo(estado));
    }

    @GetMapping("/rol/{rol}/activo/{estado}")
    public ResponseEntity<List<UsuarioResponse>> listarPorRolYActivo(@PathVariable RolUsuario rol, @PathVariable boolean estado) {
        return ResponseEntity.ok(usuarioService.listarPorRolYActivo(rol, estado));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodosUsuarios());
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioRequest request) {
        try {
            UsuarioResponse nuevoUsuario = usuarioService.crearUsuario(request);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo crear el usuario.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el usuario");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioRequest request) {
      return ResponseEntity.ok(usuarioService.actualizarUsuario(id, request));
    }

}
