package com.example.booking.controllers;

import java.util.List;
import java.util.Set;
import com.example.booking.models.Service;
import com.example.booking.services.ProfessionalService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.booking.models.Professional;
import javax.validation.Valid;

@RestController
class ProfessionalController {

    private final ProfessionalService professionalService;

    ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping("/professionals")
    Page<Professional> listProfessionals(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "+lastName") String sortBy) {
        return professionalService.listProfessionals(page, pageSize, sortBy);
    }

    @GetMapping("/professionals/{id}")
    Professional getProfessional(@PathVariable Long id) {
        return professionalService.getProfessional(id);
    }

    @PostMapping("/professionals")
    Professional createProfessional(@RequestBody @Valid Professional newProfessional) {
        return professionalService.createProfessional(newProfessional);
    }

    @PutMapping("/professionals/{id}")
    Professional updateProfessional(@PathVariable @Valid Long id, @RequestBody Professional actualProfessional) {
        return professionalService.updateProfessional(id, actualProfessional);
    }

    @DeleteMapping("/professionals/{id}")
    void deleteProfessional(@PathVariable Long id) {
        professionalService.deleteProfessional(id);
    }

    @GetMapping("/professionals/{id}/services")
    Set<Service> listServices(@PathVariable Long id) {
        return professionalService.listServices(id);
    }

    @PostMapping("/professionals/{id}/services")
    void addServices(@PathVariable Long id, @RequestBody List<Service> services) {
        professionalService.addServices(id, services);
    }

    @DeleteMapping("/professionals/{id}/services")
    void removeServices(@PathVariable Long id, @RequestBody List<Service> services) {
        professionalService.removeServices(id, services);
    }
}
