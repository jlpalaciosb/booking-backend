package com.example.agenda.services;

import com.example.agenda.models.Appointment;
import java.util.List;

public interface AppointmentService {
    List<Appointment> listAppointments();
    Appointment getAppointment(Long id);
    Appointment createAppointment(Appointment newAppointment);
    Appointment updateAppointment(Long id, Appointment actualAppointment);
    void deleteAppointment(Long id);
}
