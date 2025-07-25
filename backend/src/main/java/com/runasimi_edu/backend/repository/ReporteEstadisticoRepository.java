package com.runasimi_edu.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runasimi_edu.backend.model.ReporteEstadistico;
import com.runasimi_edu.backend.model.ReporteEstadistico.TipoReporte;
import com.runasimi_edu.backend.model.Usuario;

public interface ReporteEstadisticoRepository extends JpaRepository<ReporteEstadistico, Long> {
    List<ReporteEstadistico> findByDocente(Usuario docente);
    List<ReporteEstadistico> findByTipoReporte(TipoReporte tipo);
    List<ReporteEstadistico> findByFechaGeneracionBetween(Date inicio, Date fin);
    List<ReporteEstadistico> findByDocenteAndTipoReporte(Usuario docente, TipoReporte tipo);
}