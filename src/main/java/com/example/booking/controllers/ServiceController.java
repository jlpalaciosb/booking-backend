package com.example.booking.controllers;

import java.util.List;
import com.example.booking.services.ServiceService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.booking.models.Service;
import javax.validation.Valid;

@RestController
class ServiceController {

    private final ServiceService serviceService;

    ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/services")
    List<Service> listServices() {
        return serviceService.listServices();
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
