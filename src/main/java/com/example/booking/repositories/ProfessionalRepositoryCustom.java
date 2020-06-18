package com.example.booking.repositories;

import com.example.booking.models.Professional;

public interface ProfessionalRepositoryCustom {
    boolean existsAppointmentWithProfessional(Professional professional);
    boolean existsServiceWithId(Long id);
}
