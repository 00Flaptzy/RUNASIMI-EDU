package com.runasimi_edu.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "categorias_actividades")
public class CategoriaActividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(length = 30)
    private String icono;

    @Column(length = 7)
    private String color;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Actividad> actividades;

    public CategoriaActividad(){
        
    }

    public CategoriaActividad(List<Actividad> actividades, String color, String icono, Long id, String nombre) {
        this.actividades = actividades;
        this.color = color;
        this.icono = icono;
        this.id = id;
        this.nombre = nombre;
    }

    
}