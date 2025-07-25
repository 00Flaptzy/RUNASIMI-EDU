package com.runasimi_edu.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.SesionAlumno;
import com.runasimi_edu.backend.model.Usuario;

public interface SesionAlumnoRepository extends JpaRepository<SesionAlumno, Long> {
    List<SesionAlumno> findByAlumno(Usuario alumno);
    List<SesionAlumno> findByActividad(Actividad actividad);
    List<SesionAlumno> findByAlumnoIdAndCompletado(Long alumnoId, Boolean completado);
    List<SesionAlumno> findByFechaInicioBetween(Date inicio, Date fin);
    int countByActividadAndCompletado(Actividad actividad, Boolean completado);
    List<SesionAlumno> findByAlumnoAndCompletado(Usuario alumno, Boolean completado);
    
    // Métodos corregidos con consultas JPQL explícitas
    
    @Query("SELECT SUM(s.puntosObtenidos) FROM SesionAlumno s WHERE s.alumno.id = :alumnoId")
    Integer sumPuntosObtenidosByAlumnoId(@Param("alumnoId") Long alumnoId);
    
    @Query("SELECT SUM(s.puntosObtenidos) FROM SesionAlumno s WHERE s.alumno = :alumno")
    Integer sumPuntosObtenidosByAlumno(@Param("alumno") Usuario alumno);
}