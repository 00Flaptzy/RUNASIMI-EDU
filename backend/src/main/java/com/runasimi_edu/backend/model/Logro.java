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
@Table(name = "logros")
public class Logro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = false)
    private Usuario alumno;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoLogro tipo;

    private Integer cantidad = 1;

    @ManyToOne
    @JoinColumn(name = "actividad_id")
    private Actividad actividad;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaObtencion = new Date();

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    public Logro(){

    }

    public Logro(Actividad actividad, Usuario alumno, String descripcion, Long id, TipoLogro tipo) {
        this.actividad = actividad;
        this.alumno = alumno;
        this.descripcion = descripcion;
        this.id = id;
        this.tipo = tipo;
    }

    
    
    public enum TipoLogro {
    ESTRELLA, MEDALLA, INSIGNIA
}
}

