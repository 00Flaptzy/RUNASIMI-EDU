package com.runasimi_edu.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.dto.response.AudioActividadResponse;
import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.AudioActividad;
import com.runasimi_edu.backend.repository.AudioActividadRepository;

@Service
public class AudioActividadService {

    @Autowired
    private AudioActividadRepository audioActividadRepository;

    // Guardar o actualizar un audio
    public AudioActividad guardar(AudioActividad audioActividad) {
        return audioActividadRepository.save(audioActividad);
    }

    // Buscar por ID
    public Optional<AudioActividadResponse> buscarPorId(Long id) {
    return audioActividadRepository.findById(id)
        .map(audio -> new AudioActividadResponse(
            audio.getActividad().getId(),
            audio.getId(),
            audio.getOpciones(),
            audio.getRespuestaCorrecta(),
            audio.getRutaAudio(),
            audio.getUrlImagenOpcional()
        ));
}


    // Eliminar por ID con validación
    public void eliminarPorId(Long id) {
        if (!audioActividadRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró el audio con ID: " + id);
        }
        audioActividadRepository.deleteById(id);
    }

    // Listar todos los registros
    public List<AudioActividadResponse> listarTodosDTO() {
    List<AudioActividad> audios = audioActividadRepository.findAll();
    return audios.stream().map(audio -> new AudioActividadResponse(
            audio.getActividad().getId(),
            audio.getId(),
            audio.getOpciones(),
            audio.getRespuestaCorrecta(),
            audio.getRutaAudio(),
            audio.getUrlImagenOpcional()
    )).toList();
}

    // Buscar por actividad (entidad completa)
    public List<AudioActividad> buscarPorActividad(Actividad actividad) {
        return audioActividadRepository.findByActividad(actividad);
    }

    // Buscar por ID de actividad
    public List<AudioActividad> buscarPorActividadId(Long actividadId) {
        return audioActividadRepository.findByActividadId(actividadId);
    }

    // Verificar si ya existe un registro con esa ruta
    public boolean existePorRutaAudio(String rutaAudio) {
        return audioActividadRepository.existsByRutaAudio(rutaAudio);
    }
}
