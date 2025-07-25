package com.runasimi_edu.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.PalabraIncompleta;

public interface PalabraIncompletaRepository extends JpaRepository<PalabraIncompleta, Long> {
    List<PalabraIncompleta> findByActividad(Actividad actividad);
    List<PalabraIncompleta> findByCategoriaVocabulario(String categoria);
    List<PalabraIncompleta> findByPalabraCompletaContainingIgnoreCase(String palabra);
    List<PalabraIncompleta> findByActividadId(Long actividadId);
}