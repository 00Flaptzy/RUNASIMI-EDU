package com.runasimi_edu.backend.dto.response;

import java.util.Date;

import com.runasimi_edu.backend.model.Logro.TipoLogro;

import lombok.Data;

@Data
public class LogroResponse {
    private Long id;
    private Long alumnoId;
    private String nombreAlumno;       // opcional
    private Long actividadId;          // opcional
    private String nombreActividad;    // opcional
    private TipoLogro tipo;
    private Integer cantidad;
    private String descripcion;
    private Date fechaObtencion;

    public LogroResponse() {
    }

    public LogroResponse(Long actividadId, Long alumnoId, Integer cantidad, String descripcion, Date fechaObtencion, Long id, String nombreActividad, String nombreAlumno, TipoLogro tipo) {
        this.actividadId = actividadId;
        this.alumnoId = alumnoId;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.fechaObtencion = fechaObtencion;
        this.id = id;
        this.nombreActividad = nombreActividad;
        this.nombreAlumno = nombreAlumno;
        this.tipo = tipo;
    }
}
