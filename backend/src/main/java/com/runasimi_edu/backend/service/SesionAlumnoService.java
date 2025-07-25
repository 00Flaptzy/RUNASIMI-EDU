package com.runasimi_edu.backend.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.model.Actividad;
import com.runasimi_edu.backend.model.SesionAlumno;
import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.repository.SesionAlumnoRepository;
import com.runasimi_edu.backend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SesionAlumnoService {

    private final SesionAlumnoRepository sesionAlumnoRepository;
    private final UsuarioRepository usuarioRepository;

    // Guardar o actualizar una sesión
    public SesionAlumno guardarSesion(SesionAlumno sesion) {
        return sesionAlumnoRepository.save(sesion);
    }

    // Obtener todas las sesiones
    public List<SesionAlumno> listarTodas() {
        return sesionAlumnoRepository.findAll();
    }

    // Obtener sesiones por alumno (usando el objeto directamente)
    public List<SesionAlumno> listarPorAlumno(Usuario alumno) {
        return sesionAlumnoRepository.findByAlumno(alumno);
    }

    // Obtener sesiones por actividad
    public List<SesionAlumno> listarPorActividad(Actividad actividad) {
        return sesionAlumnoRepository.findByActividad(actividad);
    }

    // Obtener sesiones completadas por alumno (usando alumnoId)
    public List<SesionAlumno> listarPorAlumnoYCompletado(Long alumnoId, Boolean completado) {
        Usuario alumno = usuarioRepository.findById(alumnoId)
            .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado con ID: " + alumnoId));
        return sesionAlumnoRepository.findByAlumnoAndCompletado(alumno, completado);
    }

    // Buscar por ID
    public Optional<SesionAlumno> buscarPorId(Long id) {
        return sesionAlumnoRepository.findById(id);
    }

    // Eliminar por ID
    public void eliminarPorId(Long id) {
        sesionAlumnoRepository.deleteById(id);
    }

    // Buscar sesiones entre fechas
    public List<SesionAlumno> buscarPorRangoFechas(Date inicio, Date fin) {
        return sesionAlumnoRepository.findByFechaInicioBetween(inicio, fin);
    }

    // Contar sesiones completadas por actividad
    public int contarCompletadasPorActividad(Actividad actividad) {
        return sesionAlumnoRepository.countByActividadAndCompletado(actividad, true);
    }

    // Sumar puntos obtenidos por alumno (usando alumnoId)
    public Integer obtenerPuntajeTotalPorAlumno(Long alumnoId) {
        Usuario alumno = usuarioRepository.findById(alumnoId)
            .orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado con ID: " + alumnoId));
        Integer suma = sesionAlumnoRepository.sumPuntosObtenidosByAlumno(alumno);
        return (suma != null) ? suma : 0;
    }
}
