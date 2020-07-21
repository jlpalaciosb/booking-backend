package com.example.booking.controllers;

import com.example.booking.services.ClientService;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.booking.models.Client;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/clients")
@Api(tags = "Clients")
class ClientController {

    private final ClientService clientService;

    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
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

    @GetMapping("/{id}")
    @ApiOperation(value = "Find a client by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    Client getClient(@PathVariable @ApiParam(value = "Client ID", example = "0") Long id) {
        return clientService.getClient(id);
    }

    @PostMapping("/")
    @ApiOperation(value = "Add a new client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Bad Request")})
    @ApiImplicitParams({ @ApiImplicitParam(
            name = "newClient", value = "New client", dataType = "ClientPost")})
    Client createClient(@RequestBody @Valid Client newClient) {
        return clientService.createClient(newClient);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @ApiImplicitParams({ @ApiImplicitParam(
            name = "actualClient", value = "Actual client", dataType = "ClientPut")})
    Client updateClient(
            @PathVariable @ApiParam(value = "Client ID", example = "0") Long id,
            @RequestBody @Valid Client actualClient) {
        return clientService.updateClient(id, actualClient);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    void deleteClient(@PathVariable @ApiParam(value = "Client ID", example = "0") Long id) {
        clientService.deleteClient(id);
    }
}
