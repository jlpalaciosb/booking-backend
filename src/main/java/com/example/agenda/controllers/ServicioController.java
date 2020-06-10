package com.example.agenda.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.agenda.models.Servicio;
import com.example.agenda.repositories.ServicioRepository;

@RestController
class ServicioController {

    private final ServicioRepository repository;

    ServicioController(ServicioRepository repository) {
        this.repository = repository;
    }

    // Raíz de agregación

    @GetMapping("/servicios")
    List<Servicio> all() {
        return repository.findAll();
    }

    @PostMapping("/servicios")
    Servicio newServicio(@RequestBody Servicio nuevoServicio) {
        return repository.save(nuevoServicio);
    }

    // Un ítem en específico

    @GetMapping("/servicios/{id}")
    Servicio one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ServicioNotFoundException(id));
    }

    @PutMapping("/servicios/{id}")
    Servicio replaceServicio(@RequestBody Servicio nuevoServicio, @PathVariable Long id) {
        return repository.findById(id)
                .map(servicio -> {
                    servicio.setNombre(nuevoServicio.getNombre());
                    servicio.setDescripcion(nuevoServicio.getDescripcion());
                    return repository.save(servicio);
                })
                .orElseThrow(() -> new ServicioNotFoundException(id));
    }

    @DeleteMapping("/servicios/{id}")
    void deleteServicio(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ServicioNotFoundException(id);
        }
    }
}
