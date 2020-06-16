package com.example.booking.services;

import com.example.booking.models.Service;
import com.example.booking.repositories.ServiceRepository;
import com.example.booking.services.errors.BadRequestException;
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
        return serviceRepo.findById(id).orElseThrow(() -> new NotFoundException("service", id));
    }

    @Override
    public Service createService(Service newService) {
        if (newService.getName() == null) {
            throw new BadRequestException("Set service name");
        } else if (serviceRepo.existsByName(newService.getName())) {
            throw new BadRequestException("There is already a service with name = " + newService.getName());
        } else {
            return serviceRepo.save(newService);
        }
    }

    @Override
    public Service updateService(Long id, Service actualService) {
        Service service = serviceRepo.findById(id).orElseThrow(() -> new NotFoundException("service", id));
        if (actualService.getName() == null) {
            throw new BadRequestException("Set service name");
        } else if (!service.getName().equals(actualService.getName()) &&
                serviceRepo.existsByName(actualService.getName())) {
            throw new BadRequestException("There is already another service with name = " + actualService.getName());
        } else {
            service.setName(actualService.getName());
            service.setDescription(actualService.getDescription());
            return serviceRepo.save(service);
        }
    }

    @Override
    public void deleteService(Long id) {
        Service service = serviceRepo.findById(id).orElseThrow(() -> new NotFoundException("service", id));
        // TODO: validación
        serviceRepo.delete(service);
    }
}
