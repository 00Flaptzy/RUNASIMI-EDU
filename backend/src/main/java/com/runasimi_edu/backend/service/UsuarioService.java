package com.runasimi_edu.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.dto.request.UsuarioRequest;
import com.runasimi_edu.backend.dto.response.UsuarioResponse;
import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.model.Usuario.RolUsuario;
import com.runasimi_edu.backend.repository.GradoRepository;
import com.runasimi_edu.backend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final GradoRepository gradoRepository;

    // Buscar por ID
    public Optional<UsuarioResponse> buscarPorId(Long id) {
        return usuarioRepository.findById(id).map(this::convertirAResponse);
    }

    // Buscar por DNI
    public Optional<UsuarioResponse> buscarPorDni(String dni) {
        return usuarioRepository.findByDni(dni).map(this::convertirAResponse);
    }

    // Buscar por email
    public Optional<UsuarioResponse> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).map(this::convertirAResponse);
    }

    // Verificar existencia por DNI
    public boolean existePorDni(String dni) {
        return usuarioRepository.existsByDni(dni);
    }

    // Verificar existencia por email
    public boolean existePorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    // Listar usuarios por rol
    public List<UsuarioResponse> listarPorRol(RolUsuario rol) {
    return usuarioRepository.findByRol(rol).stream()
            .map(this::convertirAResponse)
            .collect(Collectors.toList());
}


    // Listar usuarios por grado
    public List<UsuarioResponse> listarPorGrado(Long gradoId) {
    return usuarioRepository.findByGradoId(gradoId).stream()
            .map(this::convertirAResponse)
            .collect(Collectors.toList());
}


    // Listar usuarios por estado activo/inactivo
    public List<Usuario> listarPorActivo(Boolean estado) {
        return usuarioRepository.findByActivo(estado);
    }

    // Listar por rol y estado
   public List<UsuarioResponse> listarPorRolYActivo(RolUsuario rol, boolean estado) {
    return usuarioRepository.findByRolAndActivo(rol, estado).stream()
            .map(this::convertirAResponse)
            .collect(Collectors.toList());
}

    // Guardar usuario directamente (interno)
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Eliminar usuario
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Listar todos los usuarios (convertidos a DTO)
    public List<UsuarioResponse> listarTodosUsuarios() {
        return usuarioRepository.findAll().stream().map(this::convertirAResponse).collect(Collectors.toList());
    }

    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request) {
    Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    // Validación y búsqueda de grado
    var grado = gradoRepository.findById(request.getGradoId())
            .orElseThrow(() -> new RuntimeException("El grado especificado no existe."));

    // Actualizar campos
    usuario.setDni(request.getDni());
    usuario.setNombreCompleto(request.getNombreCompleto());
    usuario.setEmail(request.getEmail());
    usuario.setContrasena(request.getContrasena()); // Encriptar si es necesario
    usuario.setRol(RolUsuario.valueOf(request.getRol().toUpperCase()));
    usuario.setGrado(grado);
    usuario.setActivo(request.getActivo() != null ? request.getActivo() : true);
    usuario.setUltimoAcceso(request.getUltimoAcceso());

    // Guardar cambios
    Usuario actualizado = usuarioRepository.save(usuario);
    return convertirAResponse(actualizado);
}

    // Crear usuario desde DTO
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        // Validación: DNI ya existe
        if (usuarioRepository.existsByDni(request.getDni())) {
            throw new RuntimeException("El DNI ya está registrado.");
        }

        // Validación: Email ya existe
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El correo ya está registrado.");
        }

        // Validación y búsqueda de grado
        var grado = gradoRepository.findById(request.getGradoId())
            .orElseThrow(() -> new RuntimeException("El grado especificado no existe."));

        // Crear entidad Usuario
        Usuario usuario = new Usuario();
        usuario.setDni(request.getDni());
        usuario.setNombreCompleto(request.getNombreCompleto());
        usuario.setEmail(request.getEmail());
        usuario.setContrasena(request.getContrasena()); // Idealmente encriptar con BCrypt
        usuario.setRol(RolUsuario.valueOf(request.getRol().toUpperCase()));
        usuario.setGrado(grado);
        usuario.setActivo(request.getActivo() != null ? request.getActivo() : true);
        usuario.setUltimoAcceso(request.getUltimoAcceso());

        // Guardar en base de datos
        Usuario guardado = usuarioRepository.save(usuario);

        // Convertir a Response y retornar
        return convertirAResponse(guardado);
    }

    // Método auxiliar para convertir Usuario a UsuarioResponse
    private UsuarioResponse convertirAResponse(Usuario usuario) {
        UsuarioResponse dto = new UsuarioResponse();
        dto.setId(usuario.getId());
        dto.setDni(usuario.getDni());
        dto.setNombreCompleto(usuario.getNombreCompleto());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol().name());
        dto.setFechaRegistro(usuario.getFechaRegistro());
        dto.setUltimoAcceso(usuario.getUltimoAcceso());
        dto.setActivo(usuario.getActivo());
        if (usuario.getGrado() != null) {
            dto.setGradoNombre(usuario.getGrado().getNombre());
        }
        return dto;
    }
    
}
