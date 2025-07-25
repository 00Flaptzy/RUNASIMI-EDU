package com.runasimi_edu.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.dto.request.ActividadRequest;
import com.runasimi_edu.backend.dto.response.ActividadResponse;
import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.Actividad.NivelDificultad;
import com.runasimi_edu.backend.repository.ActividadRepository;
import com.runasimi_edu.backend.repository.CategoriaActividadRepository;
import com.runasimi_edu.backend.repository.GradoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private GradoRepository gradoRepository;

    @Autowired
    private CategoriaActividadRepository categoriaActividadRepository;

    // Método guardar que faltaba
    public Actividad guardar(Actividad actividad) {
        return actividadRepository.save(actividad);
    }

    // Buscar actividad por ID
    public ActividadResponse buscarPorId(Long id) {
        Actividad actividad = actividadRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("No se encontró la actividad con ID: " + id));
        return convertirActividadResponse(actividad);
    }

    // Método para buscar entidad Actividad por ID (para uso interno)
    private Actividad buscarEntidadPorId(Long id) {
        return actividadRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Actividad no encontrada con ID: " + id));
    }

    // Eliminar por ID
    public void eliminarPorId(Long id) {
        if (!actividadRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró la actividad con ID: " + id);
        }
        actividadRepository.deleteById(id);
    }

    // Actualizar actividad
    public Actividad actualizarActividad(Actividad existente, ActividadRequest request) {
        Actividad actividad = buscarEntidadPorId(existente.getId());

        // Actualizar campos básicos
        actividad.setNombre(request.getNombre());
        actividad.setDescripcion(request.getDescripcion());
        actividad.setPuntosBase(request.getPuntosBase() != null ? request.getPuntosBase() : 0);
        actividad.setConfiguracion(request.getConfiguracion() != null ? request.getConfiguracion() : "{}");
        actividad.setMultimedia(request.getMultimedia() != null ? request.getMultimedia() : "{}");
        actividad.setUrlAudio(request.getUrlAudio());
        actividad.setUrlImagen(request.getUrlImagen());
        actividad.setUrlVideo(request.getUrlVideo());

        // Actualizar relaciones
        if (request.getGradoId() != null) {
            actividad.setGrado(
                gradoRepository.findById(request.getGradoId())
                    .orElseThrow(() -> new EntityNotFoundException("Grado no encontrado con ID: " + request.getGradoId()))
            );
        }

        if (request.getCategoriaId() != null) {
            actividad.setCategoria(
                categoriaActividadRepository.findById(request.getCategoriaId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con ID: " + request.getCategoriaId()))
            );
        }

        if (request.getNivelDificultad() != null) {
            try {
                actividad.setNivelDificultad(NivelDificultad.valueOf(request.getNivelDificultad()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Nivel de dificultad inválido: " + request.getNivelDificultad());
            }
        }

        return actividadRepository.save(actividad);
    }

    // Listar todas las actividades como DTO
    public List<ActividadResponse> listarTodas() {
        return actividadRepository.findAll()
            .stream()
            .map(this::convertirActividadResponse)
            .collect(Collectors.toList());
    }

    public List<ActividadResponse> buscarPorGradoId(Long gradoId) {
        List<Actividad> actividades = actividadRepository.findByGradoId(gradoId);
        if (actividades.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron actividades para el grado con ID: " + gradoId);
        }
        return actividades.stream()
                .map(this::convertirActividadResponse)
                .collect(Collectors.toList());
    }

    public List<ActividadResponse> buscarPorCategoriaId(Long categoriaId) {
        List<Actividad> actividades = actividadRepository.findByCategoriaId(categoriaId);
        if (actividades.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron actividades para la categoría con ID: " + categoriaId);
        }
        return actividades.stream()
                .map(this::convertirActividadResponse)
                .collect(Collectors.toList());
    }

    public List<ActividadResponse> buscarPorNivelDificultad(NivelDificultad nivel) {
        List<Actividad> actividades = actividadRepository.findByNivelDificultad(nivel);
        if (actividades.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron actividades con nivel de dificultad: " + nivel);
        }
        return actividades.stream()
                .map(this::convertirActividadResponse)
                .collect(Collectors.toList());
    }

    public List<ActividadResponse> buscarPorNombre(String nombre) {
        List<Actividad> actividades = actividadRepository.findByNombreContainingIgnoreCase(nombre);
        return actividades.stream()
                .map(this::convertirActividadResponse)
                .collect(Collectors.toList());
    }

    public List<ActividadResponse> buscarPorGradoYCategoria(Long gradoId, Long categoriaId) {
        List<Actividad> actividades = actividadRepository.findByGradoIdAndCategoriaId(gradoId, categoriaId);
        return actividades.stream()
                .map(this::convertirActividadResponse)
                .collect(Collectors.toList());
    }

    // Conversión de Entidad a DTO
    public ActividadResponse convertirActividadResponse(Actividad actividad) {
        ActividadResponse dto = new ActividadResponse();
        dto.setId(actividad.getId());
        dto.setNombre(actividad.getNombre());
        dto.setDescripcion(actividad.getDescripcion());
        dto.setCategoriaNombre(actividad.getCategoria() != null ? actividad.getCategoria().getNombre() : null);
        dto.setGradoNombre(actividad.getGrado() != null ? actividad.getGrado().getNombre() : null);
        dto.setNivelDificultad(actividad.getNivelDificultad() != null ? actividad.getNivelDificultad().name() : null);
        dto.setPuntosBase(actividad.getPuntosBase());
        dto.setConfiguracion(actividad.getConfiguracion());
        dto.setMultimedia(actividad.getMultimedia());
        dto.setUrlAudio(actividad.getUrlAudio());
        dto.setUrlImagen(actividad.getUrlImagen());
        dto.setUrlVideo(actividad.getUrlVideo());
        return dto;
    }

    // Conversión de Request a Entidad
    public Actividad convertirRequest(ActividadRequest request) {
        Actividad actividad = new Actividad();

        actividad.setNombre(request.getNombre());
        actividad.setDescripcion(request.getDescripcion());
        actividad.setPuntosBase(request.getPuntosBase() != null ? request.getPuntosBase() : 0);
        actividad.setConfiguracion(request.getConfiguracion() != null ? request.getConfiguracion() : "{}");
        actividad.setMultimedia(request.getMultimedia() != null ? request.getMultimedia() : "{}");
        actividad.setUrlAudio(request.getUrlAudio());
        actividad.setUrlImagen(request.getUrlImagen());
        actividad.setUrlVideo(request.getUrlVideo());

        // Relación con Grado
        gradoRepository.findById(request.getGradoId()).ifPresentOrElse(
            actividad::setGrado,
            () -> {
                throw new IllegalArgumentException("Grado no encontrado con ID: " + request.getGradoId());
            }
        );

        // Relación con Categoría
        categoriaActividadRepository.findById(request.getCategoriaId()).ifPresentOrElse(
            actividad::setCategoria,
            () -> {
                throw new IllegalArgumentException("Categoría no encontrada con ID: " + request.getCategoriaId());
            }
        );

        // Enum Nivel de Dificultad
        if (request.getNivelDificultad() != null) {
            try {
                actividad.setNivelDificultad(NivelDificultad.valueOf(request.getNivelDificultad()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Nivel de dificultad inválido: " + request.getNivelDificultad());
            }
        }

        return actividad;
    }
}