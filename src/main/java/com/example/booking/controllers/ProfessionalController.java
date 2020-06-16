package com.example.booking.controllers;

import java.util.List;
import com.example.booking.services.ProfessionalService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.booking.models.Professional;

@RestController
class ProfessionalController {

    private final ProfessionalService professionalService;

    ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping("/professionals")
    List<Professional> listProfessionals() {
        return professionalService.listProfessionals();
    }

    @GetMapping("/professionals/{id}")
    Professional getProfessional(@PathVariable Long id) {
        return professionalService.getProfessional(id);
    }

    @PostMapping("/professionals")
    Professional createProfessional(@RequestBody Professional newProfessional) {
        return professionalService.createProfessional(newProfessional);
    }

    @PutMapping("/professionals/{id}")
    Professional updateProfessional(@PathVariable Long id, @RequestBody Professional actualProfessional) {
        return professionalService.updateProfessional(id, actualProfessional);
    }

    @DeleteMapping("/professionals/{id}")
    void deleteProfessional(@PathVariable Long id) {
        professionalService.deleteProfessional(id);
    }
}