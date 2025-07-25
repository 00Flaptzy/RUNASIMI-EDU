package com.runasimi_edu.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.model.CategoriaActividad;
import com.runasimi_edu.backend.repository.CategoriaActividadRepository;

@Service
public class CategoriaActividadService {

    @Autowired
    private CategoriaActividadRepository categoriaActividadRepository;

    // Guardar o actualizar una categoría
    public CategoriaActividad guardar(CategoriaActividad categoria) {
        return categoriaActividadRepository.save(categoria);
    }

    // Obtener categoría por ID
    public Optional<CategoriaActividad> buscarPorId(Long id) {
        return categoriaActividadRepository.findById(id);
    }

    // Eliminar por ID con validación previa
    public void eliminarPorId(Long id) {
        if (!categoriaActividadRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró la categoría con ID: " + id);
        }
        categoriaActividadRepository.deleteById(id);
    }

    // Listar todas las categorías
    public List<CategoriaActividad> listarTodas() {
        return categoriaActividadRepository.findAll();
    }

    // Buscar categorías por nombre (coincidencia parcial, sin importar mayúsculas)
    public List<CategoriaActividad> buscarPorNombre(String nombre) {
        return categoriaActividadRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Verificar si ya existe una categoría con ese nombre exacto
    public boolean existePorNombre(String nombre) {
        return categoriaActividadRepository.existsByNombre(nombre);
    }
}
