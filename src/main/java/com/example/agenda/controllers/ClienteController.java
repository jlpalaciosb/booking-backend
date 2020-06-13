package com.example.agenda.controllers;

import java.util.List;
import com.example.agenda.services.ClienteService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.agenda.models.Cliente;

@RestController
class ClienteController {

    private final ClienteService clienteService;

    ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes")
    List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @GetMapping("/clientes/{id}")
    Cliente obtenerCliente(@PathVariable Long id) {
        return clienteService.obtenerCliente(id);
    }

    @PostMapping("/clientes")
    Cliente crearCliente(@RequestBody Cliente nuevoCliente) {
        return clienteService.crearCliente(nuevoCliente);
    }

    @PutMapping("/clientes/{id}")
    Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente actualCliente) {
        return clienteService.actualizarCliente(id, actualCliente);
    }

    @DeleteMapping("/clientes/{id}")
    void eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
    }
}
