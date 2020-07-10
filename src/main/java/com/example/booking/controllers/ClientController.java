package com.example.booking.controllers;

import com.example.booking.services.ClientService;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.booking.models.Client;
import javax.validation.Valid;

@RestController
@Api(tags = "Clients")
class ClientController {

    private final ClientService clientService;

    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/clients")
    @ApiOperation(value = "List existing clients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden")})
    Page<Client> listClients(
            @ApiParam(value = "Filter by client last name")
            @RequestParam(defaultValue = "")
            String filter,

            @ApiParam(value = "Specify an ordering. \n" +
                              "+firstName: sort by client first name, ascending. \n" +
                              "-firstName: sort by client first name, descending. \n" +
                              "+lastName: sort by client last name, ascending. \n" +
                              "-lastName: sort by client last name, descending. ")
            @RequestParam(defaultValue = "+lastName")
            String sortBy,

            @RequestParam(defaultValue = "0") Integer page,

            @RequestParam(defaultValue = "10") Integer pageSize) {
        return clientService.listClients(filter, page, pageSize, sortBy);
    }


    @GetMapping("/clients/{id}")
    @ApiOperation(value = "Find a client by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 403, message = "Forbidden")})
    Client getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }


    @PostMapping("/clients")
    @ApiOperation(value = "Add a new client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden")})
    Client createClient(
            @RequestBody @Valid
            @ApiParam(value = "New Client: \n" +
                    "Field \"id\" is ignored!, go without it, the id is auto-generated. \n" +
                    "Field \"document\" is mandatory. \n" +
                    "Field \"firstName\" is mandatory. \n" +
                    "Field \"lastName\" is mandatory. \n" +
                    "Field \"email\" is mandatory. \n" +
                    "Field \"phoneNumber\" is mandatory. \n" +
                    "Field \"birthdate\" is optional, example value: \"1990-06-15\". \n")
            Client newClient) {
        return clientService.createClient(newClient);
    }


    @PutMapping("/clients/{id}")
    @ApiOperation(value = "Update an existing client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden")})
    Client updateClient(
            @PathVariable Long id,


            @RequestBody @Valid
            @ApiParam(value = "Actual Client: \n" +
                    "Field \"id\" is ignored!, go without it, the id is in the path. \n" +
                    "Field \"document\" is mandatory. \n" +
                    "Field \"firstName\" is mandatory. \n" +
                    "Field \"lastName\" is mandatory. \n" +
                    "Field \"email\" is mandatory. \n" +
                    "Field \"phoneNumber\" is mandatory. \n" +
                    "Field \"birthdate\" is optional, example value: \"1990-06-15\". \n")
            Client actualClient) {
        return clientService.updateClient(id, actualClient);
    }


    @DeleteMapping("/clients/{id}")
    @ApiOperation(value = "Delete a client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 403, message = "Forbidden")})
    void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
