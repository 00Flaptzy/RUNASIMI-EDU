package com.runasimi_edu.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.dto.response.UsuarioResponse;
import com.runasimi_edu.backend.model.ConfiguracionDocente;
import com.runasimi_edu.backend.repository.ConfiguracionDocenteRepository;

import jakarta.transaction.Transactional;

@Service
public class ConfiguracionDocenteService {

    @Autowired
    private ConfiguracionDocenteRepository configuracionDocenteRepository;

    // Guardar nueva configuración o actualizar existente
    public ConfiguracionDocente guardar(ConfiguracionDocente configuracion) {
        return configuracionDocenteRepository.save(configuracion);
    }

    // Buscar configuración por ID
    public Optional<ConfiguracionDocente> buscarPorId(Long id) {
        return configuracionDocenteRepository.findById(id);
    }

    // Buscar configuración por docente
    public Optional<ConfiguracionDocente> buscarPorDocente(UsuarioResponse docente) {
        return configuracionDocenteRepository.findByDocente(docente);
    }

    // Verificar si existe una configuración para ese docente
    public boolean existePorDocente(UsuarioResponse docente) {
        return configuracionDocenteRepository.existsByDocente(docente);
    }

    // Eliminar por ID
    @Transactional
    public void eliminarPorId(Long id) {
        if (!configuracionDocenteRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe configuración con ID: " + id);
        }
        configuracionDocenteRepository.deleteById(id);
    }
}
