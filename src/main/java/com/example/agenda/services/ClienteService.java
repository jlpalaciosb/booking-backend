package com.example.agenda.services;

import com.example.agenda.models.Cliente;
import java.util.List;

public interface ClienteService {
    List<Cliente> listarClientes();
    Cliente obtenerCliente(Long id);
    Cliente crearCliente(Cliente nuevoCliente);
    Cliente actualizarCliente(Long id, Cliente actualCliente);
    void eliminarCliente(Long id);
}
