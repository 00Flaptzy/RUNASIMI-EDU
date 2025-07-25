package com.runasimi_edu.backend.dto.request;

import java.util.Date;

import lombok.Data;

@Data
public class SesionAlumnoRequest {
    private Long alumnoId;
    private Long actividadId;
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean completado;
    private Integer puntosObtenidos;
    private Integer intentos;
    private Integer tiempoSegundos;
    private String estado; // "EN_PROGRESO" o "COMPLETADO"
    private Integer ultimoItem;
    private String detalles;
}
