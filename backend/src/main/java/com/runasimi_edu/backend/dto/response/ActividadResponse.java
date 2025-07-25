package com.runasimi_edu.backend.dto.response;

import lombok.Data;

@Data
public class ActividadResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private String categoriaNombre;
    private String gradoNombre;
    private String nivelDificultad;
    private Integer puntosBase;
    private String configuracion;
    private String multimedia;
    private String urlVideo;
    private String urlAudio;
    private String urlImagen;

    public ActividadResponse() {
    }

    public ActividadResponse(String categoriaNombre, String configuracion, String descripcion, String gradoNombre, Long id, String multimedia, String nivelDificultad, String nombre, Integer puntosBase, String urlAudio, String urlImagen, String urlVideo) {
        this.categoriaNombre = categoriaNombre;
        this.configuracion = configuracion;
        this.descripcion = descripcion;
        this.gradoNombre = gradoNombre;
        this.id = id;
        this.multimedia = multimedia;
        this.nivelDificultad = nivelDificultad;
        this.nombre = nombre;
        this.puntosBase = puntosBase;
        this.urlAudio = urlAudio;
        this.urlImagen = urlImagen;
        this.urlVideo = urlVideo;
    }
}
