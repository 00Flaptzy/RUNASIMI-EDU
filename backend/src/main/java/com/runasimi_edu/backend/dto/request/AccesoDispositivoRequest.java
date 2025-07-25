package com.runasimi_edu.backend.dto.request;

import com.runasimi_edu.backend.model.AccesoDispositivo.TipoDispositivo;

import lombok.Data;

@Data
public class AccesoDispositivoRequest {
    private Long usuarioId;
    private TipoDispositivo tipoDispositivo;
    private String sistemaOperativo;
    private String navegador;
}
