package com.runasimi_edu.backend.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "sesiones_alumnos")
public class SesionAlumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = false)
    private Usuario alumno;

    @ManyToOne
    @JoinColumn(name = "actividad_id", nullable = false)
    private Actividad actividad;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;

    private Boolean completado = false;
    private Integer puntosObtenidos = 0;
    private Integer intentos = 0;
    private Integer tiempoSegundos = 0;

    @Enumerated(EnumType.STRING)
    private EstadoSesion estado = EstadoSesion.COMPLETADO;

    private Integer ultimoItem;

    @Column(columnDefinition = "TEXT")
    private String detalles;

    public SesionAlumno(){
        
    }

    public SesionAlumno(Actividad actividad, Usuario alumno, String detalles, Date fechaFin, Date fechaInicio, Long id, Integer ultimoItem) {
        this.actividad = actividad;
        this.alumno = alumno;
        this.detalles = detalles;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.id = id;
        this.ultimoItem = ultimoItem;
    }


    
    public enum EstadoSesion {
    EN_PROGRESO, COMPLETADO
}
}

