package com.example.booking.services;

import com.example.booking.models.Service;
import org.springframework.data.domain.Page;

public interface ServiceService {
    Page<Service> listServices(Integer page, Integer pageSize, String sortBy);
    Service getService(Long id);
    Service createService(Service newService);
    Service updateService(Long id, Service actualService);
    void deleteService(Long id);
}
