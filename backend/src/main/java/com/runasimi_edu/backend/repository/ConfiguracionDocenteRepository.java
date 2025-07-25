package com.runasimi_edu.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runasimi_edu.backend.dto.response.UsuarioResponse;
import com.runasimi_edu.backend.model.ConfiguracionDocente;

public interface ConfiguracionDocenteRepository extends JpaRepository<ConfiguracionDocente, Long> {
    Optional<ConfiguracionDocente> findByDocente(UsuarioResponse docente);
    boolean existsByDocente(UsuarioResponse docente);
}