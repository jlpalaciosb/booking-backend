package com.example.booking.controllers;

import com.example.booking.services.ServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "List existing services")
    @GetMapping("/services")
    Page<Service> listServices(
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "+name") String sortBy) {
        return serviceService.listServices(filter, page, pageSize, sortBy);
    }

    @ApiOperation(value = "Find a service by id")
    @GetMapping("/services/{id}")
    Service getService(@PathVariable Long id) {
        return serviceService.getService(id);
    }

    @ApiOperation(value = "Add a new service")
    @PostMapping("/services")
    Service createService(@RequestBody @Valid Service newService) {
        return serviceService.createService(newService);
    }

    @ApiOperation(value = "Update an existing service")
    @PutMapping("/services/{id}")
    Service updateService(@PathVariable Long id, @RequestBody @Valid Service actualService) {
        return serviceService.updateService(id, actualService);
    }

    @ApiOperation(value = "Delete a service")
    @DeleteMapping("/services/{id}")
    void deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
    }
}
