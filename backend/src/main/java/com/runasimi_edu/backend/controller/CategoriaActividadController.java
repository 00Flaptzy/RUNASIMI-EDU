package com.runasimi_edu.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.runasimi_edu.backend.model.CategoriaActividad;
import com.runasimi_edu.backend.service.CategoriaActividadService;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*") // Permitir CORS si tu frontend lo necesita
public class CategoriaActividadController {

    @Autowired
    private CategoriaActividadService categoriaActividadService;

    // Crear nueva categoría
    @PostMapping
    public ResponseEntity<CategoriaActividad> crearCategoria(@RequestBody CategoriaActividad categoria) {
        if (categoriaActividadService.existePorNombre(categoria.getNombre())) {
            return ResponseEntity.badRequest().build(); // Ya existe
        }
        CategoriaActividad nuevaCategoria = categoriaActividadService.guardar(categoria);
        return ResponseEntity.ok(nuevaCategoria);
    }

    // Obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<CategoriaActividad>> listarCategorias() {
        List<CategoriaActividad> categorias = categoriaActividadService.listarTodas();
        return ResponseEntity.ok(categorias);
    }

    // Buscar categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaActividad> obtenerPorId(@PathVariable Long id) {
        return categoriaActividadService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Buscar por coincidencia de nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<CategoriaActividad>> buscarPorNombre(@RequestParam String nombre) {
        List<CategoriaActividad> resultados = categoriaActividadService.buscarPorNombre(nombre);
        return ResponseEntity.ok(resultados);
    }

    // Actualizar categoría
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaActividad> actualizarCategoria(@PathVariable Long id, @RequestBody CategoriaActividad categoriaActualizada) {
        return categoriaActividadService.buscarPorId(id).map(categoriaExistente -> {
            categoriaExistente.setNombre(categoriaActualizada.getNombre());
            categoriaExistente.setIcono(categoriaActualizada.getIcono());
            categoriaExistente.setColor(categoriaActualizada.getColor());
            CategoriaActividad actualizada = categoriaActividadService.guardar(categoriaExistente);
            return ResponseEntity.ok(actualizada);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar categoría por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        try {
            categoriaActividadService.eliminarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
