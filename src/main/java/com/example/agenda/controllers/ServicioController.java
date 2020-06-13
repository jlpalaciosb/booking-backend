package com.example.agenda.controllers;

import java.util.List;
import com.example.agenda.services.ServicioService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.agenda.models.Servicio;

@RestController
class ServicioController {

    private final ServicioService servicioService;

    ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping("/servicios")
    List<Servicio> listarServicios() {
        return servicioService.listarServicios();
    }

    @GetMapping("/servicios/{id}")
    Servicio obtenerServicio(@PathVariable Long id) {
        return servicioService.obtenerServicio(id);
    }

    @PostMapping("/servicios")
    Servicio crearServicio(@RequestBody Servicio nuevoServicio) {
        return servicioService.crearServicio(nuevoServicio);
    }

    @PutMapping("/servicios/{id}")
    Servicio actualizarServicio(@PathVariable Long id, @RequestBody Servicio actualServicio) {
        return servicioService.actualizarServicio(id, actualServicio);
    }

    @DeleteMapping("/servicios/{id}")
    void eliminarServicio(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
    }
}
