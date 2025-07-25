package com.runasimi_edu.backend.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.runasimi_edu.backend.model.ReporteEstadistico;
import com.runasimi_edu.backend.model.ReporteEstadistico.TipoReporte;
import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.service.ReporteEstadisticoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteEstadisticoController {

    private final ReporteEstadisticoService reporteEstadisticoService;

    // Crear o actualizar reporte
    @PostMapping
    public ResponseEntity<ReporteEstadistico> guardar(@RequestBody ReporteEstadistico reporte) {
        ReporteEstadistico nuevo = reporteEstadisticoService.guardar(reporte);
        return ResponseEntity.ok(nuevo);
    }

    // Obtener todos los reportes
    @GetMapping
    public ResponseEntity<List<ReporteEstadistico>> listarTodos() {
        return ResponseEntity.ok(reporteEstadisticoService.listarTodos());
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReporteEstadistico> obtenerPorId(@PathVariable Long id) {
        Optional<ReporteEstadistico> encontrado = reporteEstadisticoService.buscarPorId(id);
        return encontrado.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reporteEstadisticoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por ID de docente
    @GetMapping("/docente/{docenteId}")
    public ResponseEntity<List<ReporteEstadistico>> buscarPorDocente(@PathVariable Long docenteId) {
        Usuario docente = new Usuario();
        docente.setId(docenteId);
        return ResponseEntity.ok(reporteEstadisticoService.buscarPorDocente(docente));
    }

    // Buscar por tipo de reporte
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<ReporteEstadistico>> buscarPorTipo(@PathVariable TipoReporte tipo) {
        return ResponseEntity.ok(reporteEstadisticoService.buscarPorTipoReporte(tipo));
    }

    // Buscar por docente y tipo de reporte
    @GetMapping("/docente/{docenteId}/tipo/{tipo}")
    public ResponseEntity<List<ReporteEstadistico>> buscarPorDocenteYTipo(
            @PathVariable Long docenteId,
            @PathVariable TipoReporte tipo) {
        Usuario docente = new Usuario();
        docente.setId(docenteId);
        return ResponseEntity.ok(reporteEstadisticoService.buscarPorDocenteYTipo(docente, tipo));
    }

    // Buscar por fechas (ejemplo simple usando timestamp como query param)
    @GetMapping("/rango-fechas")
    public ResponseEntity<List<ReporteEstadistico>> buscarPorRangoFechas(
            @RequestParam("inicio") long inicio,
            @RequestParam("fin") long fin) {
        Date fechaInicio = new Date(inicio);
        Date fechaFin = new Date(fin);
        return ResponseEntity.ok(reporteEstadisticoService.buscarPorRangoFechas(fechaInicio, fechaFin));
    }
}
