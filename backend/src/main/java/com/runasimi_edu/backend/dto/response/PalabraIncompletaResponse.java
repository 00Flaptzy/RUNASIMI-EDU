package com.runasimi_edu.backend.dto.response;

import lombok.Data;

@Data
public class PalabraIncompletaResponse {
    private Long id;
    private String palabraCompleta;
    private String palabraIncompleta;
    private String categoriaVocabulario;
    private String rutaImagen;
    private String rutaAudio;
    private String nombreActividad; // opcional, si quieres mostrar en respuesta

    public PalabraIncompletaResponse() {
    }

    public PalabraIncompletaResponse(String categoriaVocabulario, Long id, String nombreActividad, String palabraCompleta, String palabraIncompleta, String rutaAudio, String rutaImagen) {
        this.categoriaVocabulario = categoriaVocabulario;
        this.id = id;
        this.nombreActividad = nombreActividad;
        this.palabraCompleta = palabraCompleta;
        this.palabraIncompleta = palabraIncompleta;
        this.rutaAudio = rutaAudio;
        this.rutaImagen = rutaImagen;
    }
}
