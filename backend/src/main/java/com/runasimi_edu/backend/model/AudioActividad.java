package com.runasimi_edu.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "audios_actividades")
public class AudioActividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "actividad_id", nullable = false)
    private Actividad actividad;

    @Column(nullable = false, length = 255)
    private String rutaAudio;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String opciones;

    @Column(length = 100)
    private String respuestaCorrecta;

    private String urlImagenOpcional;

    public AudioActividad(){
        
    }
    public AudioActividad(Actividad actividad, Long id, String opciones, String respuestaCorrecta, String rutaAudio, String urlImagenOpcional) {
        this.actividad = actividad;
        this.id = id;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.rutaAudio = rutaAudio;
        this.urlImagenOpcional = urlImagenOpcional;
    }

    
}