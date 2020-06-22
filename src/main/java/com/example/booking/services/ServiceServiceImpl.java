package com.example.booking.services;

import com.example.booking.models.Service;
import com.example.booking.repositories.ServiceRepository;
import com.example.booking.controllers.errors.BadRequestException;
import com.example.booking.controllers.errors.NotFoundException;

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
        validate(null, newService);
        return serviceRepo.save(newService);
    }

    @Override
    public Service updateService(Long id, Service actualService) {
        Service service = serviceRepo.findById(id).orElseThrow(() -> new NotFoundException("service", id));
        validate(service, actualService);
        service.setName(actualService.getName());
        service.setDescription(actualService.getDescription());
        return serviceRepo.save(service);
    }

    @Override
    public void deleteService(Long id) {
        Service service = serviceRepo.findById(id).orElseThrow(() -> new NotFoundException("service", id));
        if (serviceRepo.existsAppointmentWithService(service)) {
            throw new BadRequestException("There is/are appointment/s associated with this service");
        } else {
            serviceRepo.removeFromProfessionalsServices(service.getId());
            serviceRepo.delete(service);
        }
    }

    private void validate(Service oldService, Service newService) {
        boolean creating = (oldService == null);
        if ((creating || !oldService.getName().equals(newService.getName())) &&
                serviceRepo.existsByName(newService.getName())) {
            throw new BadRequestException("There is another service with name = " + newService.getName());
        }
    }
}
