package com.runasimi_edu.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.Actividad.NivelDificultad;
import com.runasimi_edu.backend.model.FraseOrdenable;
import com.runasimi_edu.backend.repository.FraseOrdenableRepository;

@Service
public class FraseOrdenableService {

    @Autowired
    private FraseOrdenableRepository fraseOrdenableRepository;

    // Guardar o actualizar frase
    public FraseOrdenable guardar(FraseOrdenable frase) {
        return fraseOrdenableRepository.save(frase);
    }

    // Buscar por ID
    public Optional<FraseOrdenable> buscarPorId(Long id) {
        return fraseOrdenableRepository.findById(id);
    }

    // Eliminar por ID con validación
    public void eliminarPorId(Long id) {
        if (!fraseOrdenableRepository.existsById(id)) {
            throw new IllegalArgumentException("No existe frase ordenable con ID: " + id);
        }
        fraseOrdenableRepository.deleteById(id);
    }

    // Listar todas
    public List<FraseOrdenable> listarTodas() {
        return fraseOrdenableRepository.findAll();
    }

    // Buscar por actividad (entidad)
    public List<FraseOrdenable> buscarPorActividad(Actividad actividad) {
        return fraseOrdenableRepository.findByActividad(actividad);
    }

    // Buscar por ID de actividad
    public List<FraseOrdenable> buscarPorActividadId(Long actividadId) {
        return fraseOrdenableRepository.findByActividadId(actividadId);
    }

    // Buscar por nivel de dificultad
    public List<FraseOrdenable> buscarPorNivel(NivelDificultad nivel) {
        return fraseOrdenableRepository.findByNivel(nivel);
    }
}
