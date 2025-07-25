package com.runasimi_edu.backend.dto.response;

import com.runasimi_edu.backend.model.ConfiguracionDocente.VistaPreferida;

import lombok.Data;

@Data
public class ConfiguracionDocenteResponse {
    private Long id;
    private Long docenteId;
    private VistaPreferida vistaPreferida;
    private String filtrosGuardados;
    private Boolean notificaciones;

    public ConfiguracionDocenteResponse() {
    }

    public ConfiguracionDocenteResponse(Long docenteId, String filtrosGuardados, Long id, Boolean notificaciones, VistaPreferida vistaPreferida) {
        this.docenteId = docenteId;
        this.filtrosGuardados = filtrosGuardados;
        this.id = id;
        this.notificaciones = notificaciones;
        this.vistaPreferida = vistaPreferida;
    }
}
