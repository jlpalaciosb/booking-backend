package com.example.booking.controllers;

import com.example.booking.services.ClientService;
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

    @GetMapping("/clients")
    Page<Client> listClients(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String sortBy) {
        return clientService.listClients(page, pageSize, sortBy);
    }

    @GetMapping("/clients/{id}")
    Client getClient(@PathVariable Long id) {
        return clientService.getClient(id);
    }

    @PostMapping("/clients")
    Client createClient(@RequestBody @Valid Client newClient) {
        return clientService.createClient(newClient);
    }

    @PutMapping("/clients/{id}")
    Client updateClient(@PathVariable Long id, @RequestBody @Valid Client actualClient) {
        return clientService.updateClient(id, actualClient);
    }

    @DeleteMapping("/clients/{id}")
    void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
