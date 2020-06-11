package com.example.agenda.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.agenda.models.Profesional;
import com.example.agenda.repositories.ProfesionalRepository;

@RestController
class ProfesionalController {

    private final ProfesionalRepository repository;

    ProfesionalController(ProfesionalRepository repository) {
        this.repository = repository;
    }

    // Raíz de agregación

    @GetMapping("/profesionales")
    List<Profesional> listarProfesionales() {
        return repository.findAll();
    }

    @PostMapping("/profesionales")
    Profesional crearProfesional(@RequestBody Profesional nuevoProfesional) {
        return repository.save(nuevoProfesional);
    }

    // Un ítem en específico

    @GetMapping("/profesionales/{id}")
    Profesional obtenerProfesional(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("profesional", id));
    }

    @PutMapping("/profesionales/{id}")
    Profesional actualizarProfesional(@PathVariable Long id, @RequestBody Profesional actualProfesional) {
        return repository.findById(id)
                .map(profesional -> {
                    profesional.setDocumento(actualProfesional.getDocumento());
                    profesional.setNombre(actualProfesional.getNombre());
                    profesional.setApellido(actualProfesional.getApellido());
                    profesional.setCorreo(actualProfesional.getCorreo());
                    profesional.setTelefono(actualProfesional.getTelefono());
                    profesional.setFechaNacimiento(actualProfesional.getFechaNacimiento());
                    return repository.save(profesional);
                })
                .orElseThrow(() -> new EntityNotFoundException("profesional", id));
    }

    @DeleteMapping("/profesionales/{id}")
    void eliminarProfesional(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("profesional", id);
        }
    }
}
