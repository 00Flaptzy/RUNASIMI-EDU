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
@Table(name = "reportes_estadisticos")
public class ReporteEstadistico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "docente_id", nullable = false)
    private Usuario docente;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGeneracion = new Date();

    @Column(columnDefinition = "TEXT")
    private String rangoFechas;

    @ManyToOne
    @JoinColumn(name = "grado_id")
    private Grado grado;

    @Enumerated(EnumType.STRING)
    private TipoReporte tipoReporte;

    @Column(columnDefinition = "TEXT")
    private String datos;

    @Column(columnDefinition = "TEXT")
    private String compartidoCon;
    
    public ReporteEstadistico(){

    }

    public ReporteEstadistico(String compartidoCon, String datos, Usuario docente, Grado grado, Long id, String rangoFechas, TipoReporte tipoReporte) {
        this.compartidoCon = compartidoCon;
        this.datos = datos;
        this.docente = docente;
        this.grado = grado;
        this.id = id;
        this.rangoFechas = rangoFechas;
        this.tipoReporte = tipoReporte;
    }

   
    public enum TipoReporte {
    AVANCE, RENDIMIENTO, COMPARATIVO
    }
}

