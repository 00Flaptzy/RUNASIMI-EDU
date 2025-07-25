package com.runasimi_edu.backend.dto.request;

import lombok.Data;

@Data
public class PalabraIncompletaRequest {
    private Long actividadId;
    private String palabraCompleta;
    private String palabraIncompleta;
    private String categoriaVocabulario;
    private String rutaImagen;
    private String rutaAudio;
}
