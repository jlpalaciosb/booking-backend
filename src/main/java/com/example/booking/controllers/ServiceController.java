package com.example.booking.controllers;

import com.example.booking.services.ServiceService;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.booking.models.Service;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/services")
@Api(tags = "Services")
class ServiceController {

    private final ServiceService serviceService;

    ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/")
    @ApiOperation(value = "List existing services")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden")})
    Page<Service> listServices(
            @ApiParam(value = "Filter by service name")
            @RequestParam(defaultValue = "")
            String filter,
            @ApiParam(value = "Specify an ordering. \n" +
                              "+name: sort by service name, ascending. \n" +
                              "-name: sort by service name, descending. ")
            @RequestParam(defaultValue = "+name")
            String sortBy,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return serviceService.listServices(filter, page, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a service by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    Service getService(@PathVariable @ApiParam(value = "Service ID", example = "0") Long id) {
        return serviceService.getService(id);
    }

    @PostMapping("/")
    @ApiOperation(value = "Add a new service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden")})
    @ApiImplicitParams({ @ApiImplicitParam(
            name = "newService", value = "New service", dataType = "ServicePost")})
    Service createService(@RequestBody @Valid Service newService) {
        return serviceService.createService(newService);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @ApiImplicitParams({ @ApiImplicitParam(
            name = "actualService", value = "Actual service", dataType = "ServicePut")})
    Service updateService(
            @PathVariable @ApiParam(value = "Service ID", example = "0") Long id,
            @RequestBody @Valid Service actualService) {
        return serviceService.updateService(id, actualService);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    void deleteService(@PathVariable @ApiParam(value = "Service ID", example = "0") Long id) {
        serviceService.deleteService(id);
    }
}
