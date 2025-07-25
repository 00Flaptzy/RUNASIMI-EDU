package com.runasimi_edu.backend.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.runasimi_edu.backend.model.AccesoDispositivo;
import com.runasimi_edu.backend.model.AccesoDispositivo.TipoDispositivo;
import com.runasimi_edu.backend.model.Usuario;
import com.runasimi_edu.backend.repository.AccesoDispositivoRepository;
import com.runasimi_edu.backend.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/accesos")
public class AccesoDispositivoController {

    @Autowired
    private AccesoDispositivoRepository accesoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los accesos
    @GetMapping
    public List<AccesoDispositivo> obtenerTodos() {
        return accesoRepository.findAll();
    }

    // Obtener acceso por ID de usuario
    @GetMapping("/usuario/{usuarioId}")
    public List<AccesoDispositivo> obtenerPorUsuario(@PathVariable Long usuarioId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        return usuarioOpt.map(accesoRepository::findByUsuario).orElse(List.of());
    }

    // Obtener accesos por tipo de dispositivo
    @GetMapping("/tipo/{tipo}")
    public List<AccesoDispositivo> obtenerPorTipoDispositivo(@PathVariable TipoDispositivo tipo) {
        return accesoRepository.findByTipoDispositivo(tipo);
    }

    // Obtener accesos entre fechas
    @GetMapping("/rango-fechas")
    public List<AccesoDispositivo> obtenerPorRangoFechas(
            @RequestParam("inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
            @RequestParam("fin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
        return accesoRepository.findByFechaAccesoBetween(inicio, fin);
    }

    // Obtener accesos por usuario y tipo de dispositivo
    @GetMapping("/usuario/{usuarioId}/tipo/{tipo}")
    public List<AccesoDispositivo> obtenerPorUsuarioYTipo(
            @PathVariable Long usuarioId,
            @PathVariable TipoDispositivo tipo) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        return usuarioOpt.map(usuario -> accesoRepository.findByUsuarioAndTipoDispositivo(usuario, tipo))
                         .orElse(List.of());
    }

    // Obtener los últimos 10 accesos
    @GetMapping("/ultimos")
    public List<AccesoDispositivo> obtenerUltimos() {
        return accesoRepository.findTop10ByOrderByFechaAccesoDesc();
    }

    // Registrar nuevo acceso
    @PostMapping
    public AccesoDispositivo registrarAcceso(@RequestBody AccesoDispositivo acceso) {
        acceso.setFechaAcceso(new Date()); // asigna fecha actual automáticamente
        return accesoRepository.save(acceso);
    }

    // Eliminar acceso por ID
    @DeleteMapping("/{id}")
    public void eliminarAcceso(@PathVariable Long id) {
        accesoRepository.deleteById(id);
    }
}
