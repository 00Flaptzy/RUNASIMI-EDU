package com.runasimi_edu.backend.dto.request;

import lombok.Data;

@Data
public class GradoRequest {
    private String nombre;
    private String descripcion;
    private Integer orden;
}
