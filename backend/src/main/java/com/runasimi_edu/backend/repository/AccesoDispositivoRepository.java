package com.runasimi_edu.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runasimi_edu.backend.model.AccesoDispositivo;
import com.runasimi_edu.backend.model.AccesoDispositivo.TipoDispositivo;
import com.runasimi_edu.backend.model.Usuario;

public interface AccesoDispositivoRepository extends JpaRepository<AccesoDispositivo, Long> {
    List<AccesoDispositivo> findByUsuario(Usuario usuario);
    List<AccesoDispositivo> findByTipoDispositivo(TipoDispositivo tipo);
    List<AccesoDispositivo> findByFechaAccesoBetween(Date inicio, Date fin);
    List<AccesoDispositivo> findByUsuarioAndTipoDispositivo(Usuario usuario, TipoDispositivo tipo);
    List<AccesoDispositivo> findTop10ByOrderByFechaAccesoDesc();
}