package com.example.agenda.controllers;

import java.util.List;
import com.example.agenda.services.ClientService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.agenda.models.Client;

@RestController
class ClienteController {

    private final ClientService clientService;

    ClienteController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clientes")
    List<Client> listarClientes() {
        return clientService.listClients();
    }

    @GetMapping("/clientes/{id}")
    Client obtenerCliente(@PathVariable Long id) {
        return clientService.getClient(id);
    }

    @PostMapping("/clientes")
    Client crearCliente(@RequestBody Client newClient) {
        return clientService.createClient(newClient);
    }

    @PutMapping("/clientes/{id}")
    Client actualizarCliente(@PathVariable Long id, @RequestBody Client actualClient) {
        return clientService.updateClient(id, actualClient);
    }

    @DeleteMapping("/clientes/{id}")
    void eliminarCliente(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
