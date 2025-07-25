package com.runasimi_edu.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.model.Usuario.RolUsuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByDni(String dni);
    Optional<Usuario> findByEmail(String email);
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
    List<Usuario> findByRol(RolUsuario rol);
    List<Usuario> findByGradoId(Long gradoId);
    List<Usuario> findByActivo(Boolean activo);
    List<Usuario> findByRolAndActivo(RolUsuario alumno, boolean b);
}