package com.runasimi_edu.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.model.Logro;
import com.runasimi_edu.backend.model.Logro.TipoLogro;
import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.repository.LogroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogroService {

    private final LogroRepository logroRepository;

    // Registrar nuevo logro
    public Logro registrar(Logro logro) {
        return logroRepository.save(logro);
    }

    // Listar todos los logros
    public List<Logro> listarTodos() {
        return logroRepository.findAll();
    }

    // Buscar logro por ID
    public Optional<Logro> buscarPorId(Long id) {
        return logroRepository.findById(id);
    }

    // Listar logros por alumno
    public List<Logro> listarPorAlumno(Usuario alumno) {
        return logroRepository.findByAlumno(alumno);
    }

    // Listar logros por tipo
    public List<Logro> listarPorTipo(TipoLogro tipo) {
        return logroRepository.findByTipo(tipo);
    }

    // Listar logros por alumno y tipo
    public List<Logro> listarPorAlumnoYTipo(Long alumnoId, TipoLogro tipo) {
        return logroRepository.findByAlumnoIdAndTipo(alumnoId, tipo);
    }

    // Contar logros por alumno
    public int contarPorAlumno(Usuario alumno) {
        return logroRepository.countByAlumno(alumno);
    }

    // Eliminar logro
    public void eliminar(Long id) {
        logroRepository.deleteById(id);
    }

    // Actualizar logro
    public Logro actualizar(Logro logro) {
        return logroRepository.save(logro);
    }
}
