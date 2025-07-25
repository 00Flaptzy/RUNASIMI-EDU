package com.runasimi_edu.backend.dto.response;

import lombok.Data;

@Data
public class CategoriaActividadResponse {
    private Long id;
    private String nombre;
    private String icono;
    private String color;

    public CategoriaActividadResponse() {
    }

    public CategoriaActividadResponse(String color, String icono, Long id, String nombre) {
        this.color = color;
        this.icono = icono;
        this.id = id;
        this.nombre = nombre;
    }
}
