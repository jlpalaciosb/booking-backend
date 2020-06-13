package com.example.agenda.services;

import com.example.agenda.models.Servicio;
import com.example.agenda.repositories.ServicioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepo;

    public ServicioServiceImpl(ServicioRepository servicioRepo) {
        this.servicioRepo = servicioRepo;
    }

    @Override
    public List<Servicio> listarServicios() {
        return servicioRepo.findAll();
    }

    @Override
    public Servicio obtenerServicio(Long id) {
        return servicioRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("servicio", id));
    }

    @Override
    public Servicio crearServicio(Servicio nuevoServicio) {
        return servicioRepo.save(nuevoServicio);
    }

    @Override
    public Servicio actualizarServicio(Long id, Servicio actualServicio) {
        return servicioRepo.findById(id)
                .map(servicio -> {
                    servicio.setNombre(actualServicio.getNombre());
                    servicio.setDescripcion(actualServicio.getDescripcion());
                    return servicioRepo.save(servicio);
                })
                .orElseThrow(() -> new EntityNotFoundException("servicio", id));
    }

    @Override
    public void eliminarServicio(Long id) {
        if (servicioRepo.existsById(id)) {
            servicioRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("servicio", id);
        }
    }
}
