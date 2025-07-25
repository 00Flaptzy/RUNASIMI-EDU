package com.runasimi_edu.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.dto.response.GradoResponse;
import com.runasimi_edu.backend.model.Grado;
import com.runasimi_edu.backend.repository.GradoRepository;

@Service
public class GradoService {

    private final GradoRepository gradoRepository;

    public GradoService(GradoRepository gradoRepository) {
        this.gradoRepository = gradoRepository;
    }

    // Retorna lista de GradoResponse para evitar bucles y controlar salida
    public List<GradoResponse> listarGradosOrdenados() {
        List<Grado> grados = gradoRepository.findByOrderByOrdenAsc();
        return grados.stream()
                .map(this::convertirAGradoResponse)
                .collect(Collectors.toList());
    }

    private GradoResponse convertirAGradoResponse(Grado grado) {
        int cantidadUsuarios = grado.getUsuarios() != null ? grado.getUsuarios().size() : 0;
        int cantidadActividades = grado.getActividades() != null ? grado.getActividades().size() : 0;

        return new GradoResponse(
            cantidadActividades,
            cantidadUsuarios,
            grado.getDescripcion(),
            grado.getId(),
            grado.getNombre(),
            grado.getOrden()
        );
    }

    // Otros métodos pueden seguir trabajando con entidad Grado si no se exponen al cliente
    public Optional<Grado> buscarPorId(Long id) {
        return gradoRepository.findById(id);
    }

    public Grado crearGrado(Grado grado) {
        if (gradoRepository.existsByNombre(grado.getNombre())) {
            throw new RuntimeException("Ya existe un grado con el nombre: " + grado.getNombre());
        }
        return gradoRepository.save(grado);
    }

    public Grado actualizarGrado(Long id, Grado nuevoGrado) {
        return gradoRepository.findById(id).map(gradoExistente -> {
            gradoExistente.setNombre(nuevoGrado.getNombre());
            gradoExistente.setDescripcion(nuevoGrado.getDescripcion());
            gradoExistente.setOrden(nuevoGrado.getOrden());
            return gradoExistente;
        }).orElseThrow(() -> new RuntimeException("Grado no encontrado con ID: " + id));
    }

    public void eliminarGrado(Long id) {
        if (!gradoRepository.existsById(id)) {
            throw new RuntimeException("No existe grado con ID: " + id);
        }
        gradoRepository.deleteById(id);
    }

    public List<Grado> buscarPorNombre(String nombre) {
        return gradoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
