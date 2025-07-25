package com.runasimi_edu.backend.dto.response;

import lombok.Data;

@Data
public class AudioActividadResponse {
    private Long id;
    private Long actividadId;
    private String rutaAudio;
    private String opciones;
    private String respuestaCorrecta;
    private String urlImagenOpcional;

    public AudioActividadResponse() {
    }

    public AudioActividadResponse(Long actividadId, Long id, String opciones, String respuestaCorrecta, String rutaAudio, String urlImagenOpcional) {
        this.actividadId = actividadId;
        this.id = id;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.rutaAudio = rutaAudio;
        this.urlImagenOpcional = urlImagenOpcional;
    }
}
