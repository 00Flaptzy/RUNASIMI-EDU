package com.runasimi_edu.backend.model;


import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 8)
    private String dni;

    @Column(nullable = false, length = 100)
    private String nombreCompleto;

    @Column(length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolUsuario rol;

    @ManyToOne
    @JoinColumn(name = "grado_id")
    private Grado grado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcceso;

    private Boolean activo = true;

    // Relaciones
    @OneToMany(mappedBy = "usuario")
    private List<AccesoDispositivo> accesosDispositivos;

    @OneToMany(mappedBy = "alumno")
    private List<SesionAlumno> sesiones;

    @OneToMany(mappedBy = "alumno")
    private List<Logro> logros;

    @OneToMany(mappedBy = "docente")
    private List<ConfiguracionDocente> configuraciones;

    @OneToMany(mappedBy = "docente")
    private List<ReporteEstadistico> reportes;
    
    public Usuario(){
        
    }

    public Usuario(List<AccesoDispositivo> accesosDispositivos, List<ConfiguracionDocente> configuraciones, String contrasena, String dni, String email, Grado grado, Long id, List<Logro> logros, String nombreCompleto, List<ReporteEstadistico> reportes, RolUsuario rol, List<SesionAlumno> sesiones, Date ultimoAcceso) {
        this.accesosDispositivos = accesosDispositivos;
        this.configuraciones = configuraciones;
        this.contrasena = contrasena;
        this.dni = dni;
        this.email = email;
        this.grado = grado;
        this.id = id;
        this.logros = logros;
        this.nombreCompleto = nombreCompleto;
        this.reportes = reportes;
        this.rol = rol;
        this.sesiones = sesiones;
        this.ultimoAcceso = ultimoAcceso;
    }

  
    public enum RolUsuario {
    ALUMNO, DOCENTE, ADMIN
}
}

