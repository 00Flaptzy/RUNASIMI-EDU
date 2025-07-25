package com.runasimi_edu.backend.dto.response;

import com.runasimi_edu.backend.model.Actividad.NivelDificultad;

import lombok.Data;

@Data
public class FraseOrdenableResponse {
    private Long id;
    private Long actividadId;
    private String fraseCorrecta;
    private String palabrasDesordenadas;
    private String traduccionEspanol;
    private NivelDificultad nivel;
    private String urlAudio;
    private String urlImagen;

    public FraseOrdenableResponse() {
    }

    public FraseOrdenableResponse(Long actividadId, String fraseCorrecta, Long id, NivelDificultad nivel, String palabrasDesordenadas, String traduccionEspanol, String urlAudio, String urlImagen) {
        this.actividadId = actividadId;
        this.fraseCorrecta = fraseCorrecta;
        this.id = id;
        this.nivel = nivel;
        this.palabrasDesordenadas = palabrasDesordenadas;
        this.traduccionEspanol = traduccionEspanol;
        this.urlAudio = urlAudio;
        this.urlImagen = urlImagen;
    }
}
