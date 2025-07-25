package com.runasimi_edu.backend.dto.request;

import com.runasimi_edu.backend.model.Logro.TipoLogro;

import lombok.Data;

@Data
public class LogroRequest {
    private Long alumnoId;
    private Long actividadId; // puede ser null
    private TipoLogro tipo;
    private Integer cantidad;
    private String descripcion;
}
