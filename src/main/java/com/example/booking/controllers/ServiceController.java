package com.example.booking.controllers;

import com.example.booking.services.ServiceService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.booking.models.Service;
import javax.validation.Valid;

@RestController
class ServiceController {

    private final ServiceService serviceService;

    ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/services")
    Page<Service> listServices(
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "+name") String sortBy) {
        return serviceService.listServices(filter, page, pageSize, sortBy);
    }

    @GetMapping("/services/{id}")
    Service getService(@PathVariable Long id) {
        return serviceService.getService(id);
    }

    @PostMapping("/services")
    Service createService(@RequestBody @Valid Service newService) {
        return serviceService.createService(newService);
    }

    @PutMapping("/services/{id}")
    Service updateService(@PathVariable Long id, @RequestBody @Valid Service actualService) {
        return serviceService.updateService(id, actualService);
    }

    @DeleteMapping("/services/{id}")
    void deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
    }
}
