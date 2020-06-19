package com.example.booking.controllers;

import java.util.List;
import com.example.booking.services.ClientService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.booking.models.Client;
import javax.validation.Valid;

@RestController
class ClientController {

    private final ClientService clientService;

    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    List<Client> listClients() {
        return clientService.listClients();
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
