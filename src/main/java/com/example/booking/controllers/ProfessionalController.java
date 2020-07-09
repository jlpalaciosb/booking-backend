package com.example.booking.controllers;

import java.util.List;
import java.util.Set;
import com.example.booking.models.Service;
import com.example.booking.services.ProfessionalService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "List existing professionals")
    @GetMapping("/professionals")
    Page<Professional> listProfessionals(
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "+lastName") String sortBy) {
        return professionalService.listProfessionals(filter, page, pageSize, sortBy);
    }

    @ApiOperation(value = "Find a professional by id")
    @GetMapping("/professionals/{id}")
    Professional getProfessional(@PathVariable Long id) {
        return professionalService.getProfessional(id);
    }

    @ApiOperation(value = "Add a new professional")
    @PostMapping("/professionals")
    Professional createProfessional(@RequestBody @Valid Professional newProfessional) {
        return professionalService.createProfessional(newProfessional);
    }

    @ApiOperation(value = "Update an existing professional")
    @PutMapping("/professionals/{id}")
    Professional updateProfessional(@PathVariable @Valid Long id, @RequestBody Professional actualProfessional) {
        return professionalService.updateProfessional(id, actualProfessional);
    }

    @ApiOperation(value = "Delete a professional")
    @DeleteMapping("/professionals/{id}")
    void deleteProfessional(@PathVariable Long id) {
        professionalService.deleteProfessional(id);
    }

    @ApiOperation(value = "List services provided by a professional")
    @GetMapping("/professionals/{id}/services")
    Set<Service> listServices(@PathVariable Long id) {
        return professionalService.listServices(id);
    }

    @ApiOperation(value = "Add services to a professional list of services")
    @PostMapping("/professionals/{id}/services")
    void addServices(@PathVariable Long id, @RequestBody List<Service> services) {
        professionalService.addServices(id, services);
    }

    @ApiOperation(value = "Remove services from a professional list of services")
    @DeleteMapping("/professionals/{id}/services")
    void removeServices(@PathVariable Long id, @RequestBody List<Service> services) {
        professionalService.removeServices(id, services);
    }
}
