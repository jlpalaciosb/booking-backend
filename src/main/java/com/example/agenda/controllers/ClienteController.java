package com.example.agenda.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.agenda.models.Cliente;
import com.example.agenda.repositories.ClienteRepository;

@RestController
class ClienteController {

    private final ClienteRepository repository;

    ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    // Raíz de agregación

    @GetMapping("/clientes")
    List<Cliente> listarClientes() {
        return repository.findAll();
    }

    @PostMapping("/clientes")
    Cliente crearCliente(@RequestBody Cliente nuevoCliente) {
        return repository.save(nuevoCliente);
    }

    // Un ítem en específico

    @GetMapping("/clientes/{id}")
    Cliente obtenerCliente(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("cliente", id));
    }

    @PutMapping("/clientes/{id}")
    Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente actualCliente) {
        return repository.findById(id)
                .map(cliente -> {
                    cliente.setDocumento(actualCliente.getDocumento());
                    cliente.setNombre(actualCliente.getNombre());
                    cliente.setApellido(actualCliente.getApellido());
                    cliente.setCorreo(actualCliente.getCorreo());
                    cliente.setTelefono(actualCliente.getTelefono());
                    cliente.setFechaNacimiento(actualCliente.getFechaNacimiento());
                    return repository.save(cliente);
                })
                .orElseThrow(() -> new EntityNotFoundException("cliente", id));
    }

    @DeleteMapping("/clientes/{id}")
    void eliminarCliente(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("cliente", id);
        }
    }
}
