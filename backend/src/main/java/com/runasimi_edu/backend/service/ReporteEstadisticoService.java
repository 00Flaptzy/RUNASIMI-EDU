package com.runasimi_edu.backend.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.model.ReporteEstadistico;
import com.runasimi_edu.backend.model.ReporteEstadistico.TipoReporte;
import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.repository.ReporteEstadisticoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReporteEstadisticoService {

    private final ReporteEstadisticoRepository reporteEstadisticoRepository;

    // Guardar o actualizar
    public ReporteEstadistico guardar(ReporteEstadistico reporte) {
        return reporteEstadisticoRepository.save(reporte);
    }

    // Listar todos
    public List<ReporteEstadistico> listarTodos() {
        return reporteEstadisticoRepository.findAll();
    }

    // Buscar por ID
    public Optional<ReporteEstadistico> buscarPorId(Long id) {
        return reporteEstadisticoRepository.findById(id);
    }

    // Eliminar por ID
    public void eliminar(Long id) {
        reporteEstadisticoRepository.deleteById(id);
    }

    // Buscar por docente
    public List<ReporteEstadistico> buscarPorDocente(Usuario docente) {
        return reporteEstadisticoRepository.findByDocente(docente);
    }

    // Buscar por tipo de reporte
    public List<ReporteEstadistico> buscarPorTipoReporte(TipoReporte tipo) {
        return reporteEstadisticoRepository.findByTipoReporte(tipo);
    }

    // Buscar por rango de fechas
    public List<ReporteEstadistico> buscarPorRangoFechas(Date inicio, Date fin) {
        return reporteEstadisticoRepository.findByFechaGeneracionBetween(inicio, fin);
    }

    // Buscar por docente y tipo de reporte
    public List<ReporteEstadistico> buscarPorDocenteYTipo(Usuario docente, TipoReporte tipo) {
        return reporteEstadisticoRepository.findByDocenteAndTipoReporte(docente, tipo);
    }
}
