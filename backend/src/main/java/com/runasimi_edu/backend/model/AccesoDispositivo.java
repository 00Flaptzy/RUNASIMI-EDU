package com.runasimi_edu.backend.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "accesos_dispositivos")
public class AccesoDispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private TipoDispositivo tipoDispositivo;

    @Column(length = 50)
    private String sistemaOperativo;

    @Column(length = 50)
    private String navegador;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAcceso = new Date();

    public AccesoDispositivo(){
        
    }

    public AccesoDispositivo(Long id, String navegador, String sistemaOperativo, TipoDispositivo tipoDispositivo, Usuario usuario) {
        this.id = id;
        this.navegador = navegador;
        this.sistemaOperativo = sistemaOperativo;
        this.tipoDispositivo = tipoDispositivo;
        this.usuario = usuario;
    }

    
    
    public enum TipoDispositivo {
    TABLET, LAPTOP, NAVEGADOR
}
}

