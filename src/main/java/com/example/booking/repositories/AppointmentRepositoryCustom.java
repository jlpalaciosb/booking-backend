package com.example.booking.repositories;

import com.example.booking.models.Appointment;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepositoryCustom {
    List<Appointment> findAll(LocalDate minDate, LocalDate maxDate, Long clientId);
}
