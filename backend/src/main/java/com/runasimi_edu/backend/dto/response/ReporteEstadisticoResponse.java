package com.runasimi_edu.backend.dto.response;

import java.util.Date;

import com.runasimi_edu.backend.model.ReporteEstadistico.TipoReporte;

import lombok.Data;

@Data
public class ReporteEstadisticoResponse {
    private Long id;
    private String docenteNombre;
    private String gradoNombre;
    private Date fechaGeneracion;
    private String rangoFechas;
    private TipoReporte tipoReporte;
    private String datos;
    private String compartidoCon;

    public ReporteEstadisticoResponse() {
    }

    public ReporteEstadisticoResponse(String compartidoCon, String datos, String docenteNombre, Date fechaGeneracion, String gradoNombre, Long id, String rangoFechas, TipoReporte tipoReporte) {
        this.compartidoCon = compartidoCon;
        this.datos = datos;
        this.docenteNombre = docenteNombre;
        this.fechaGeneracion = fechaGeneracion;
        this.gradoNombre = gradoNombre;
        this.id = id;
        this.rangoFechas = rangoFechas;
        this.tipoReporte = tipoReporte;
    }
}
