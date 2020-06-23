package com.example.booking.services;

import com.example.booking.models.Appointment;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {
    List<Appointment> listAppointments(LocalDate minDate, LocalDate maxDate, Long clientId);
    Appointment getAppointment(Long id);
    Appointment createAppointment(Appointment newAppointment);
    Appointment updateAppointment(Long id, Appointment actualAppointment);
    void deleteAppointment(Long id);
}
