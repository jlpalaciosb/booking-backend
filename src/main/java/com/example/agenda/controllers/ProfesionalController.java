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
    List<Profesional> all() {
        return repository.findAll();
    }

    @PostMapping("/profesionales")
    Profesional newProfesional(@RequestBody Profesional nuevoProfesional) {
        return repository.save(nuevoProfesional);
    }

    // Un ítem en específico

    @GetMapping("/profesionales/{id}")
    Profesional one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("profesional", id));
    }

    @PutMapping("/profesionales/{id}")
    Profesional replaceProfesional(@RequestBody Profesional nuevoProfesional, @PathVariable Long id) {
        return repository.findById(id)
                .map(profesional -> {
                    profesional.setDocumento(nuevoProfesional.getDocumento());
                    profesional.setNombre(nuevoProfesional.getNombre());
                    profesional.setApellido(nuevoProfesional.getApellido());
                    profesional.setCorreo(nuevoProfesional.getCorreo());
                    profesional.setTelefono(nuevoProfesional.getTelefono());
                    profesional.setFechaNacimiento(nuevoProfesional.getFechaNacimiento());
                    return repository.save(profesional);
                })
                .orElseThrow(() -> new EntityNotFoundException("profesional", id));
    }

    @DeleteMapping("/profesionales/{id}")
    void deleteProfesional(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("profesional", id);
        }
    }
}
