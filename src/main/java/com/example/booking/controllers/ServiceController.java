package com.example.booking.controllers;

import com.example.booking.services.ServiceService;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.booking.models.Service;
import javax.validation.Valid;

@RestController
@Api(tags = "Services")
class ServiceController {

    private final ServiceService serviceService;

    private final String exampleValue = "{\n  \"name\": \"string\"\n}";

    ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }


    @GetMapping("/services")
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


    @GetMapping("/services/{id}")
    @ApiOperation(value = "Find a service by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 403, message = "Forbidden")})
    Service getService(@PathVariable Long id) {
        return serviceService.getService(id);
    }


    @PostMapping("/services")
    @ApiOperation(value = "Add a new service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden")})
    Service createService(
            @RequestBody @Valid
            @ApiParam(value = "New Service: \n" +
                    "Field \"id\" is ignored!, go without it, the id is auto-generated. \n" +
                    "Field \"name\" is mandatory. \n" +
                    "Field \"description\" is optional. \n")
            Service newService) {
        return serviceService.createService(newService);
    }


    @PutMapping("/services/{id}")
    @ApiOperation(value = "Update an existing service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden")})
    Service updateService(
            @PathVariable Long id,

            @RequestBody @Valid
            @ApiParam(value = "Actual Service: \n" +
                    "Field \"id\" is ignored!, go without it, the id is in the path. \n" +
                    "Field \"name\" is mandatory. \n" +
                    "Field \"description\" is optional. \n")
            Service actualService) {
        return serviceService.updateService(id, actualService);
    }


    @DeleteMapping("/services/{id}")
    @ApiOperation(value = "Delete a service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 403, message = "Forbidden")})
    void deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
    }
}
