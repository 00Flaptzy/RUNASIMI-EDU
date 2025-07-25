package com.runasimi_edu.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.Actividad.NivelDificultad;

@Repository // ✅ Es buena práctica anotarlo aunque no es obligatorio
public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    // Buscar todas las actividades por ID de grado
    List<Actividad> findByGradoId(Long gradoId);

    // Buscar todas las actividades por ID de categoría
    List<Actividad> findByCategoriaId(Long categoriaId);

    // Buscar actividades por nivel de dificultad (ENUM)
    List<Actividad> findByNivelDificultad(NivelDificultad nivel);

    // Buscar por coincidencia en el nombre (ignorando mayúsculas y minúsculas)
    List<Actividad> findByNombreContainingIgnoreCase(String nombre);

    // Buscar actividades por grado y categoría
    List<Actividad> findByGradoIdAndCategoriaId(Long gradoId, Long categoriaId);
}
