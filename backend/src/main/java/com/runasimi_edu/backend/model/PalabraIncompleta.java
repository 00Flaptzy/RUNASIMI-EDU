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
@Table(name = "palabras_incompletas")
public class PalabraIncompleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "actividad_id", nullable = false)
    private Actividad actividad;

    @Column(nullable = false, length = 50)
    private String palabraCompleta;

    @Column(nullable = false, length = 50)
    private String palabraIncompleta;

    @Column(length = 50)
    private String categoriaVocabulario;

    private String rutaImagen;
    private String rutaAudio;

    public PalabraIncompleta(){

    }

    public PalabraIncompleta(Actividad actividad, String categoriaVocabulario, Long id, String palabraCompleta, String palabraIncompleta, String rutaAudio, String rutaImagen) {
        this.actividad = actividad;
        this.categoriaVocabulario = categoriaVocabulario;
        this.id = id;
        this.palabraCompleta = palabraCompleta;
        this.palabraIncompleta = palabraIncompleta;
        this.rutaAudio = rutaAudio;
        this.rutaImagen = rutaImagen;
    }

    
}