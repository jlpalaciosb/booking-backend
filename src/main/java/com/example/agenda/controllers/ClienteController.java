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
    List<Cliente> all() {
        return repository.findAll();
    }

    @PostMapping("/clientes")
    Cliente newCliente(@RequestBody Cliente nuevoCliente) {
        return repository.save(nuevoCliente);
    }

    // Un ítem en específico

    @GetMapping("/clientes/{id}")
    Cliente one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("cliente", id));
    }

    @PutMapping("/clientes/{id}")
    Cliente replaceCliente(@RequestBody Cliente nuevoCliente, @PathVariable Long id) {
        return repository.findById(id)
                .map(cliente -> {
                    cliente.setDocumento(nuevoCliente.getDocumento());
                    cliente.setNombre(nuevoCliente.getNombre());
                    cliente.setApellido(nuevoCliente.getApellido());
                    cliente.setCorreo(nuevoCliente.getCorreo());
                    cliente.setTelefono(nuevoCliente.getTelefono());
                    cliente.setFechaNacimiento(nuevoCliente.getFechaNacimiento());
                    return repository.save(cliente);
                })
                .orElseThrow(() -> new EntityNotFoundException("cliente", id));
    }

    @DeleteMapping("/clientes/{id}")
    void deleteCliente(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("cliente", id);
        }
    }
}
