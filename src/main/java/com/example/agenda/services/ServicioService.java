package com.example.agenda.services;

import com.example.agenda.models.Servicio;
import java.util.List;

public interface ServicioService {
    List<Servicio> listarServicios();
    Servicio obtenerServicio(Long id);
    Servicio crearServicio(Servicio nuevoServicio);
    Servicio actualizarServicio(Long id, Servicio actualServicio);
    void eliminarServicio(Long id);
}
