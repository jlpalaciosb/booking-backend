package com.example.booking.services;

import com.example.booking.models.Service;
import java.util.List;

public interface ServiceService {
    List<Service> listServices();
    Service getService(Long id);
    Service createService(Service newService);
    Service updateService(Long id, Service actualService);
    void deleteService(Long id);
}
