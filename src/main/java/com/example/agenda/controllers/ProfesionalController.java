package com.example.agenda.controllers;

import java.util.List;
import com.example.agenda.services.ProfessionalService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.agenda.models.Professional;

@RestController
class ProfesionalController {

    private final ProfessionalService professionalService;

    ProfesionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping("/profesionales")
    List<Professional> listarProfesionales() {
        return professionalService.listProfessionals();
    }

    @GetMapping("/profesionales/{id}")
    Professional obtenerProfesional(@PathVariable Long id) {
        return professionalService.getProfessional(id);
    }

    @PostMapping("/profesionales")
    Professional crearProfesional(@RequestBody Professional newProfessional) {
        return professionalService.createProfessional(newProfessional);
    }

    @PutMapping("/profesionales/{id}")
    Professional actualizarProfesional(@PathVariable Long id, @RequestBody Professional actualProfessional) {
        return professionalService.updateProfessional(id, actualProfessional);
    }

    @DeleteMapping("/profesionales/{id}")
    void eliminarProfesional(@PathVariable Long id) {
        professionalService.deleteProfessional(id);
    }
}
