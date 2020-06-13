package com.example.agenda.services;

import com.example.agenda.models.Service;
import com.example.agenda.repositories.ServiceRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class ServicioServiceImpl implements ServicioService {

    private final ServiceRepository servicioRepo;

    public ServicioServiceImpl(ServiceRepository servicioRepo) {
        this.servicioRepo = servicioRepo;
    }

    @Override
    public List<Service> listarServicios() {
        return servicioRepo.findAll();
    }

    @Override
    public Service obtenerServicio(Long id) {
        return servicioRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("servicio", id));
    }

    @Override
    public Service crearServicio(Service newService) {
        return servicioRepo.save(newService);
    }

    @Override
    public Service actualizarServicio(Long id, Service actualService) {
        return servicioRepo.findById(id)
                .map(servicio -> {
                    servicio.setName(actualService.getName());
                    servicio.setDescription(actualService.getDescription());
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
