package com.runasimi_edu.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runasimi_edu.backend.model.Grado;

public interface GradoRepository extends JpaRepository<Grado, Long> {
    List<Grado> findByOrderByOrdenAsc();
    boolean existsByNombre(String nombre);
    List<Grado> findByNombreContainingIgnoreCase(String nombre);
}