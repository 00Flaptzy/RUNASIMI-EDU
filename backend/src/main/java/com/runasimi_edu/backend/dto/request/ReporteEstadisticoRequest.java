package com.runasimi_edu.backend.dto.request;

import com.runasimi_edu.backend.model.ReporteEstadistico.TipoReporte;

import lombok.Data;

@Data
public class ReporteEstadisticoRequest {
    private Long docenteId;
    private String rangoFechas;
    private Long gradoId;
    private TipoReporte tipoReporte;
    private String datos;
    private String compartidoCon;
}
