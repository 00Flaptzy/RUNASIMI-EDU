package com.runasimi_edu.backend.dto.request;

import lombok.Data;

@Data
public class ActividadRequest {
    private String nombre;
    private String descripcion;
    private Long categoriaId;
    private Long gradoId;
    private String nivelDificultad;
    private Integer puntosBase;
    private String configuracion;
    private String multimedia;
    private String urlVideo;
    private String urlAudio;
    private String urlImagen;
}
