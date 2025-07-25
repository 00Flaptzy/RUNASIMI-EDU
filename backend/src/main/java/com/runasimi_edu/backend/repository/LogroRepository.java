package com.runasimi_edu.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runasimi_edu.backend.model.Logro;
import com.runasimi_edu.backend.model.Logro.TipoLogro;
import com.runasimi_edu.backend.model.Usuario;

public interface LogroRepository extends JpaRepository<Logro, Long> {
    List<Logro> findByAlumno(Usuario alumno);
    List<Logro> findByTipo(TipoLogro tipo);
    List<Logro> findByAlumnoIdAndTipo(Long alumnoId, TipoLogro tipo);
    int countByAlumno(Usuario alumno);
}