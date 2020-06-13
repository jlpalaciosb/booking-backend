package com.example.agenda.services;

import com.example.agenda.models.Service;
import java.util.List;

public interface ServicioService {
    List<Service> listarServicios();
    Service obtenerServicio(Long id);
    Service crearServicio(Service newService);
    Service actualizarServicio(Long id, Service actualService);
    void eliminarServicio(Long id);
}
