package com.example.agenda.controllers;

import java.util.List;
import com.example.agenda.services.ProfesionalService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.agenda.models.Profesional;

@RestController
class ProfesionalController {

    private final ProfesionalService profesionalService;

    ProfesionalController(ProfesionalService profesionalService) {
        this.profesionalService = profesionalService;
    }

    @GetMapping("/profesionales")
    List<Profesional> listarProfesionales() {
        return profesionalService.listarProfesionales();
    }

    @GetMapping("/profesionales/{id}")
    Profesional obtenerProfesional(@PathVariable Long id) {
        return profesionalService.obtenerProfesional(id);
    }

    @PostMapping("/profesionales")
    Profesional crearProfesional(@RequestBody Profesional nuevoProfesional) {
        return profesionalService.crearProfesional(nuevoProfesional);
    }

    @PutMapping("/profesionales/{id}")
    Profesional actualizarProfesional(@PathVariable Long id, @RequestBody Profesional actualProfesional) {
        return profesionalService.actualizarProfesional(id, actualProfesional);
    }

    @DeleteMapping("/profesionales/{id}")
    void eliminarProfesional(@PathVariable Long id) {
        profesionalService.eliminarProfesional(id);
    }
}
