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
import com.example.agenda.models.Service;

@RestController
class ServicioController {

    private final ServicioService servicioService;

    ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping("/servicios")
    List<Service> listarServicios() {
        return servicioService.listarServicios();
    }

    @GetMapping("/servicios/{id}")
    Service obtenerServicio(@PathVariable Long id) {
        return servicioService.obtenerServicio(id);
    }

    @PostMapping("/servicios")
    Service crearServicio(@RequestBody Service newService) {
        return servicioService.crearServicio(newService);
    }

    @PutMapping("/servicios/{id}")
    Service actualizarServicio(@PathVariable Long id, @RequestBody Service actualService) {
        return servicioService.actualizarServicio(id, actualService);
    }

    @DeleteMapping("/servicios/{id}")
    void eliminarServicio(@PathVariable Long id) {
        servicioService.eliminarServicio(id);
    }
}
