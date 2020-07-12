package com.example.booking.controllers;

import java.util.List;
import java.util.Set;
import com.example.booking.models.Service;
import com.example.booking.services.ProfessionalService;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.booking.models.Professional;
import javax.validation.Valid;

@RestController
@Api(tags = "Professionals")
class ProfessionalController {

    private final ProfessionalService professionalService;

    ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping("/professionals")
    @ApiOperation(value = "List existing professionals")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden")})
    Page<Professional> listProfessionals(
            @ApiParam(value = "Filter by professional last name")
            @RequestParam(defaultValue = "")
            String filter,
            @ApiParam(value = "Specify an ordering. \n" +
                              "+firstName: sort by professional first name, ascending. \n" +
                              "-firstName: sort by professional first name, descending. \n" +
                              "+lastName: sort by professional last name, ascending. \n" +
                              "-lastName: sort by professional last name, descending. ")
            @RequestParam(defaultValue = "+lastName")
            String sortBy,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        return professionalService.listProfessionals(filter, page, pageSize, sortBy);
    }

    @GetMapping("/professionals/{id}")
    @ApiOperation(value = "Find a professional by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    Professional getProfessional(@PathVariable @ApiParam(value = "Professional ID") Long id) {
        return professionalService.getProfessional(id);
    }

    @PostMapping("/professionals")
    @ApiOperation(value = "Add a new professional")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden")})
    @ApiImplicitParams({ @ApiImplicitParam(
            name = "newProfessional", value = "New professional", dataType = "ProfessionalPost")})
    Professional createProfessional(@RequestBody @Valid Professional newProfessional) {
        return professionalService.createProfessional(newProfessional);
    }

    @PutMapping("/professionals/{id}")
    @ApiOperation(value = "Update an existing professional")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @ApiImplicitParams({ @ApiImplicitParam(
            name = "actualProfessional", value = "Actual professional", dataType = "ProfessionalPut")})
    Professional updateProfessional(
            @PathVariable @ApiParam(value = "Professional ID") Long id,
            @RequestBody @Valid Professional actualProfessional) {
        return professionalService.updateProfessional(id, actualProfessional);
    }

    @DeleteMapping("/professionals/{id}")
    @ApiOperation(value = "Delete a professional")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    void deleteProfessional(@PathVariable @ApiParam(value = "Professional ID") Long id) {
        professionalService.deleteProfessional(id);
    }

    @GetMapping("/professionals/{id}/services")
    @ApiOperation(value = "Get a professional list of services")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden")})
    Set<Service> listServices(@PathVariable @ApiParam(value = "Professional ID") Long id) {
        return professionalService.listServices(id);
    }

    @PostMapping("/professionals/{id}/services")
    @ApiOperation(value = "Add services to a professional list of services")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @ApiImplicitParams({ @ApiImplicitParam(
            name = "services", allowMultiple = true, value = "Services to add",
            dataType = "ProfessionalServicePost")})
    void addServices(
            @PathVariable @ApiParam(value = "Professional ID") Long id,
            @RequestBody List<Service> services) {
        professionalService.addServices(id, services);
    }

    @DeleteMapping("/professionals/{id}/services")
    @ApiOperation(value = "Remove services from a professional list of services")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @ApiImplicitParams({ @ApiImplicitParam(
            name = "services", allowMultiple = true, value = "Services to remove",
            dataType = "ProfessionalServiceDelete")})
    void removeServices(
            @PathVariable @ApiParam(value = "Professional ID") Long id,
            @RequestBody List<Service> services) {
        professionalService.removeServices(id, services);
    }
}
