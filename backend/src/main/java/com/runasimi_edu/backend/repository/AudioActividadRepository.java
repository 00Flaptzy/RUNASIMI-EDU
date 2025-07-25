package com.runasimi_edu.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.AudioActividad;

public interface AudioActividadRepository extends JpaRepository<AudioActividad, Long> {
    List<AudioActividad> findByActividad(Actividad actividad);
    List<AudioActividad> findByActividadId(Long actividadId);
    boolean existsByRutaAudio(String rutaAudio);
}