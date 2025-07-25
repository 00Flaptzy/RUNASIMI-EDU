package com.runasimi_edu.backend.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runasimi_edu.backend.model.CategoriaActividad;

public interface CategoriaActividadRepository extends JpaRepository<CategoriaActividad, Long> {
    List<CategoriaActividad> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByNombre(String nombre);
}