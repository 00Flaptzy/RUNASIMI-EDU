package com.runasimi_edu.backend.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.Actividad.NivelDificultad;
import com.runasimi_edu.backend.model.FraseOrdenable;

public interface FraseOrdenableRepository extends JpaRepository<FraseOrdenable, Long> {
    List<FraseOrdenable> findByActividad(Actividad actividad);
    List<FraseOrdenable> findByNivel(NivelDificultad nivel);
    List<FraseOrdenable> findByActividadId(Long actividadId);
}