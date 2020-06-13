package com.example.agenda.services;

import com.example.agenda.models.Cliente;
import com.example.agenda.repositories.ClienteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepo;

    public ClienteServiceImpl(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public Cliente obtenerCliente(Long id) {
        return clienteRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("cliente", id));
    }

    @Override
    public Cliente crearCliente(Cliente nuevoCliente) {
        return clienteRepo.save(nuevoCliente);
    }

    @Override
    public Cliente actualizarCliente(Long id, Cliente actualCliente) {
        return clienteRepo.findById(id)
                .map(cliente -> {
                    cliente.setDocumento(actualCliente.getDocumento());
                    cliente.setNombre(actualCliente.getNombre());
                    cliente.setApellido(actualCliente.getApellido());
                    cliente.setCorreo(actualCliente.getCorreo());
                    cliente.setTelefono(actualCliente.getTelefono());
                    cliente.setFechaNacimiento(actualCliente.getFechaNacimiento());
                    return clienteRepo.save(cliente);
                })
                .orElseThrow(() -> new EntityNotFoundException("cliente", id));
    }

    @Override
    public void eliminarCliente(Long id) {
        if (clienteRepo.existsById(id)) {
            clienteRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("cliente", id);
        }
    }
}
