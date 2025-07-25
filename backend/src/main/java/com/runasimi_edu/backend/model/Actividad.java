package com.runasimi_edu.backend.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "actividades")
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaActividad categoria;

    @ManyToOne
    @JoinColumn(name = "grado_id", nullable = false)
    private Grado grado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelDificultad nivelDificultad;

    private Integer puntosBase = 0;

    @Column(columnDefinition = "TEXT")
    private String configuracion;

    @Column(columnDefinition = "TEXT")
    private String multimedia;

    private String urlVideo;
    private String urlAudio;
    private String urlImagen;

    // Relaciones
    @OneToMany(mappedBy = "actividad")
    private List<SesionAlumno> sesionesAlumnos;

    @OneToMany(mappedBy = "actividad")
    private List<Logro> logros;

    @OneToMany(mappedBy = "actividad")
    private List<FraseOrdenable> frasesOrdenables;

    @OneToMany(mappedBy = "actividad")
    private List<AudioActividad> audiosActividades;

    @OneToMany(mappedBy = "actividad")
    private List<PalabraIncompleta> palabrasIncompletas;

    public Actividad(){
        
    }

    public Actividad(List<AudioActividad> audiosActividades, CategoriaActividad categoria, String configuracion, String descripcion, List<FraseOrdenable> frasesOrdenables, Grado grado, Long id, List<Logro> logros, String multimedia, NivelDificultad nivelDificultad, String nombre, List<PalabraIncompleta> palabrasIncompletas, List<SesionAlumno> sesionesAlumnos, String urlAudio, String urlImagen, String urlVideo) {
        this.audiosActividades = audiosActividades;
        this.categoria = categoria;
        this.configuracion = configuracion;
        this.descripcion = descripcion;
        this.frasesOrdenables = frasesOrdenables;
        this.grado = grado;
        this.id = id;
        this.logros = logros;
        this.multimedia = multimedia;
        this.nivelDificultad = nivelDificultad;
        this.nombre = nombre;
        this.palabrasIncompletas = palabrasIncompletas;
        this.sesionesAlumnos = sesionesAlumnos;
        this.urlAudio = urlAudio;
        this.urlImagen = urlImagen;
        this.urlVideo = urlVideo;
    }

    // Getters y Setters
    
    public enum NivelDificultad {
    BASICO, INTERMEDIO, AVANZADO
}
}

