package com.example.booking.controllers;

import com.example.booking.services.ClientService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.example.booking.models.Client;
import javax.validation.Valid;

@RestController
class ClientController {

    private final ClientService clientService;

    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation(value = "List existing clients")
    @GetMapping("/clients")
    Page<Client> listClients(
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "+lastName") String sortBy) {
        return clientService.listClients(filter, page, pageSize, sortBy);
    }

    @ApiOperation(value = "Find a client by id")
    @GetMapping("/clients/{id}")
    Client getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }

    @ApiOperation(value = "Add a new client")
    @PostMapping("/clients")
    Client createClient(@RequestBody @Valid Client newClient) {
        return clientService.createClient(newClient);
    }

    @ApiOperation(value = "Update an existing client")
    @PutMapping("/clients/{id}")
    Client updateClient(@PathVariable Long id, @RequestBody @Valid Client actualClient) {
        return clientService.updateClient(id, actualClient);
    }

    @ApiOperation(value = "Delete a client")
    @DeleteMapping("/clients/{id}")
    void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
