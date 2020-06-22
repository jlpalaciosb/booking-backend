package com.example.booking.repositories;

import com.example.booking.models.Service;

public interface ServiceRepositoryCustom {
    boolean existsAppointmentWithService(Service service);
    /* Remove the service from services lists of professionals offering the service */
    void removeFromProfessionalsServices(Service service);
}
