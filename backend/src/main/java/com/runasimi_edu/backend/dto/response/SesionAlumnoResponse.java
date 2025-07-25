package com.runasimi_edu.backend.dto.response;

import java.util.Date;

import lombok.Data;

@Data
public class SesionAlumnoResponse {
    private Long id;
    private Long alumnoId;
    private String nombreAlumno;
    private Long actividadId;
    private String nombreActividad;
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean completado;
    private Integer puntosObtenidos;
    private Integer intentos;
    private Integer tiempoSegundos;
    private String estado;
    private Integer ultimoItem;
    private String detalles;

    public SesionAlumnoResponse() {
    }

    public SesionAlumnoResponse(Long actividadId, Long alumnoId, Boolean completado, String detalles, String estado, Date fechaFin, Date fechaInicio, Long id, Integer intentos, String nombreActividad, String nombreAlumno, Integer puntosObtenidos, Integer tiempoSegundos, Integer ultimoItem) {
        this.actividadId = actividadId;
        this.alumnoId = alumnoId;
        this.completado = completado;
        this.detalles = detalles;
        this.estado = estado;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.id = id;
        this.intentos = intentos;
        this.nombreActividad = nombreActividad;
        this.nombreAlumno = nombreAlumno;
        this.puntosObtenidos = puntosObtenidos;
        this.tiempoSegundos = tiempoSegundos;
        this.ultimoItem = ultimoItem;
    }
}
