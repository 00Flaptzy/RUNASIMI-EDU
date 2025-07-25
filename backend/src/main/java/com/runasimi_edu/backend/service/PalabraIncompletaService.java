package com.runasimi_edu.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.PalabraIncompleta;
import com.runasimi_edu.backend.repository.PalabraIncompletaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PalabraIncompletaService {

    private final PalabraIncompletaRepository palabraIncompletaRepository;

    // Guardar una nueva palabra
    public PalabraIncompleta guardar(PalabraIncompleta palabra) {
        return palabraIncompletaRepository.save(palabra);
    }

    // Obtener todas las palabras
    public List<PalabraIncompleta> listarTodas() {
        return palabraIncompletaRepository.findAll();
    }

    // Obtener por ID
    public Optional<PalabraIncompleta> buscarPorId(Long id) {
        return palabraIncompletaRepository.findById(id);
    }

    // Eliminar por ID
    public void eliminarPorId(Long id) {
        palabraIncompletaRepository.deleteById(id);
    }

    // Buscar por actividad
    public List<PalabraIncompleta> listarPorActividad(Actividad actividad) {
        return palabraIncompletaRepository.findByActividad(actividad);
    }

    // Buscar por ID de actividad
    public List<PalabraIncompleta> listarPorActividadId(Long actividadId) {
        return palabraIncompletaRepository.findByActividadId(actividadId);
    }

    // Buscar por categoría de vocabulario
    public List<PalabraIncompleta> listarPorCategoria(String categoria) {
        return palabraIncompletaRepository.findByCategoriaVocabulario(categoria);
    }

    // Buscar por coincidencia parcial de palabra completa
    public List<PalabraIncompleta> buscarPorPalabra(String palabra) {
        return palabraIncompletaRepository.findByPalabraCompletaContainingIgnoreCase(palabra);
    }

    // Actualizar palabra existente
    public PalabraIncompleta actualizar(Long id, PalabraIncompleta nuevaPalabra) {
        return palabraIncompletaRepository.findById(id)
                .map(palabra -> {
                    palabra.setActividad(nuevaPalabra.getActividad());
                    palabra.setPalabraCompleta(nuevaPalabra.getPalabraCompleta());
                    palabra.setPalabraIncompleta(nuevaPalabra.getPalabraIncompleta());
                    palabra.setCategoriaVocabulario(nuevaPalabra.getCategoriaVocabulario());
                    palabra.setRutaImagen(nuevaPalabra.getRutaImagen());
                    palabra.setRutaAudio(nuevaPalabra.getRutaAudio());
                    return palabraIncompletaRepository.save(palabra);
                }).orElseThrow(() -> new RuntimeException("Palabra no encontrada con ID: " + id));
    }
}
