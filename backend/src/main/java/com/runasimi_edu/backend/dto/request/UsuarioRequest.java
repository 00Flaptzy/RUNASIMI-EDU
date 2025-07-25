package com.runasimi_edu.backend.dto.request;


import java.util.Date;

import lombok.Data;

@Data
public class UsuarioRequest {
    private String dni;
    private String nombreCompleto;
    private String email;
    private String contrasena;
    private String rol;          // Debes validar si viene ALUMNO, DOCENTE o ADMIN
    private Long gradoId;        // Solo se envía el ID del grado
    private Date ultimoAcceso;   // Opcional
    private Boolean activo = true;

    public UsuarioRequest() {
    }

    public UsuarioRequest(String contrasena, String dni, String email, Long gradoId, String nombreCompleto, String rol, Date ultimoAcceso) {
        this.contrasena = contrasena;
        this.dni = dni;
        this.email = email;
        this.gradoId = gradoId;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
        this.ultimoAcceso = ultimoAcceso;
    }
}