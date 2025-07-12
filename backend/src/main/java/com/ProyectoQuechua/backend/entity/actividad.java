package com.ProyectoQuechua.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name=actividad)
public class actividad {

    @Id//marca    este campo como la clave principal de la entidad
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY) //indica que este valor   se autoincrementa
    private Loong id_actividad;

    @Column(name="titulo",nullable=false,length=30)
    private String titulo;
    @Column(name="descripcion",columnDefinition="TEXT")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name="id_tipo",nullable=false)
    private tipo_actividad 
}
