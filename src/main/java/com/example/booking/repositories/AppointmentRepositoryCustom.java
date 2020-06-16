package com.example.booking.repositories;

import com.example.booking.models.Appointment;

public interface AppointmentRepositoryCustom {
    Appointment getByCriteria(String criteria); // ejemplo
}
