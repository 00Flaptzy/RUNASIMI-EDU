package com.runasimi_edu.backend.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "configuraciones_docentes")
public class ConfiguracionDocente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "docente_id", nullable = false)
    private Usuario docente;

    @Enumerated(EnumType.STRING)
    private VistaPreferida vistaPreferida = VistaPreferida.TABLA;

    @Column(columnDefinition = "TEXT")
    private String filtrosGuardados;

    private Boolean notificaciones = true;

    public ConfiguracionDocente(){

    }
    
    public ConfiguracionDocente(Usuario docente, String filtrosGuardados, Long id) {
        this.docente = docente;
        this.filtrosGuardados = filtrosGuardados;
        this.id = id;
    }

    
    
    public enum VistaPreferida {
    TABLA, GRAFICO
}
}

