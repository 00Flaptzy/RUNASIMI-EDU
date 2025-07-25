package com.runasimi_edu.backend.dto.request;

import com.runasimi_edu.backend.model.ConfiguracionDocente.VistaPreferida;

import lombok.Data;

@Data
public class ConfiguracionDocenteRequest {
    private Long docenteId;
    private VistaPreferida vistaPreferida;
    private String filtrosGuardados;
    private Boolean notificaciones;
}
