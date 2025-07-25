package com.runasimi_edu.backend.dto.response;

import java.util.Date;

import com.runasimi_edu.backend.model.AccesoDispositivo.TipoDispositivo;

import lombok.Data;

@Data
public class AccesoDispositivoResponse {
    private Long id;
    private Long usuarioId;
    private String nombreUsuario;
    private TipoDispositivo tipoDispositivo;
    private String sistemaOperativo;
    private String navegador;
    private Date fechaAcceso;

    public AccesoDispositivoResponse() {
    }

    public AccesoDispositivoResponse(Date fechaAcceso, Long id, String navegador, String nombreUsuario, String sistemaOperativo, TipoDispositivo tipoDispositivo, Long usuarioId) {
        this.fechaAcceso = fechaAcceso;
        this.id = id;
        this.navegador = navegador;
        this.nombreUsuario = nombreUsuario;
        this.sistemaOperativo = sistemaOperativo;
        this.tipoDispositivo = tipoDispositivo;
        this.usuarioId = usuarioId;
    }
}

