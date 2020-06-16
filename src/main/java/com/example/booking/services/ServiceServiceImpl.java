package com.example.booking.services;

import com.example.booking.models.Service;
import com.example.booking.repositories.ServiceRepository;
import com.example.booking.services.errors.NotFoundException;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepo;

    public ServiceServiceImpl(ServiceRepository serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    @Override
    public List<Service> listServices() {
        return serviceRepo.findAll();
    }

    @Override
    public Service getService(Long id) {
        return serviceRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("service", id));
    }

    @Override
    public Service createService(Service newService) {
        return serviceRepo.save(newService);
    }

    @Override
    public Service updateService(Long id, Service actualService) {
        return serviceRepo.findById(id)
                .map(servicio -> {
                    servicio.setName(actualService.getName());
                    servicio.setDescription(actualService.getDescription());
                    return serviceRepo.save(servicio);
                })
                .orElseThrow(() -> new NotFoundException("service", id));
    }

    @Override
    public void deleteService(Long id) {
        if (serviceRepo.existsById(id)) {
            serviceRepo.deleteById(id);
        } else {
            throw new NotFoundException("service", id);
        }
    }
}
