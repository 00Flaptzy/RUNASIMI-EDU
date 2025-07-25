package com.runasimi_edu.backend.dto.request;

import lombok.Data;

@Data
public class AudioActividadRequest {
    private Long actividadId;
    private String rutaAudio;
    private String opciones;
    private String respuestaCorrecta;
    private String urlImagenOpcional;
}
