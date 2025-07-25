package com.runasimi_edu.backend.dto.response;

import java.util.Date;

import lombok.Data;

@Data
public class UsuarioResponse {
    private Long id;
    private String dni;
    private String nombreCompleto;
    private String email;
    private String rol;
    private String gradoNombre;
    private Date fechaRegistro;
    private Date ultimoAcceso;
    private Boolean activo;

    public UsuarioResponse() {}

    public UsuarioResponse(Boolean activo, String dni, String email, Date fechaRegistro, String gradoNombre, Long id, String nombreCompleto, String rol, Date ultimoAcceso) {
        this.activo = activo;
        this.dni = dni;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.gradoNombre = gradoNombre;
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
        this.ultimoAcceso = ultimoAcceso;
    }
}
