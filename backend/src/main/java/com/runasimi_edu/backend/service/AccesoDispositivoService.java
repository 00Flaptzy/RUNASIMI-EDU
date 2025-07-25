package com.runasimi_edu.backend.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runasimi_edu.backend.model.AccesoDispositivo;
import com.runasimi_edu.backend.model.AccesoDispositivo.TipoDispositivo;
import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.repository.AccesoDispositivoRepository;

@Service
public class AccesoDispositivoService {

    @Autowired
    private AccesoDispositivoRepository accesoDispositivoRepository;

    // Guardar un nuevo acceso
    public AccesoDispositivo guardar(AccesoDispositivo accesoDispositivo) {
        return accesoDispositivoRepository.save(accesoDispositivo);
    }

    // Buscar por ID
    public Optional<AccesoDispositivo> buscarPorId(Long id) {
        return accesoDispositivoRepository.findById(id);
    }

    // Listar todos los accesos
    public List<AccesoDispositivo> listarTodos() {
        return accesoDispositivoRepository.findAll();
    }

    // Listar accesos por usuario
    public List<AccesoDispositivo> listarPorUsuario(Usuario usuario) {
        return accesoDispositivoRepository.findByUsuario(usuario);
    }

    // Listar accesos por tipo de dispositivo
    public List<AccesoDispositivo> listarPorTipoDispositivo(TipoDispositivo tipo) {
        return accesoDispositivoRepository.findByTipoDispositivo(tipo);
    }

    // Listar accesos entre fechas
    public List<AccesoDispositivo> listarPorRangoFechas(Date inicio, Date fin) {
        return accesoDispositivoRepository.findByFechaAccesoBetween(inicio, fin);
    }

    // Listar accesos por usuario y tipo de dispositivo
    public List<AccesoDispositivo> listarPorUsuarioYTipo(Usuario usuario, TipoDispositivo tipo) {
        return accesoDispositivoRepository.findByUsuarioAndTipoDispositivo(usuario, tipo);
    }

    // Obtener los 10 últimos accesos registrados
    public List<AccesoDispositivo> ultimos10Accesos() {
        return accesoDispositivoRepository.findTop10ByOrderByFechaAccesoDesc();
    }

    // Eliminar por ID
    public void eliminarPorId(Long id) {
        if (!accesoDispositivoRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró el acceso con ID: " + id);
        }
        accesoDispositivoRepository.deleteById(id);
    }
}
