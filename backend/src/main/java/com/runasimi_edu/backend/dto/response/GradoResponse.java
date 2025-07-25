package com.runasimi_edu.backend.dto.response;


import lombok.Data;

@Data
public class GradoResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer orden;
    private int cantidadUsuarios;
    private int cantidadActividades;

    public GradoResponse() {
    }

    public GradoResponse(int cantidadActividades, int cantidadUsuarios, String descripcion, Long id, String nombre, Integer orden) {
        this.cantidadActividades = cantidadActividades;
        this.cantidadUsuarios = cantidadUsuarios;
        this.descripcion = descripcion;
        this.id = id;
        this.nombre = nombre;
        this.orden = orden;
    }

}
