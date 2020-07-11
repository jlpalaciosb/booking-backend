package com.example.booking.services;

import com.example.booking.models.Appointment;
import com.example.booking.models.Appointment.AppointmentStatus;
import org.springframework.data.domain.Page;
import java.time.LocalDate;

public interface AppointmentService {
    Page<Appointment> listAppointments(LocalDate minDate, LocalDate maxDate, Long clientId,
                                       Integer page, Integer pageSize);
    Appointment getAppointment(Long id);
    Appointment createAppointment(Appointment newAppointment);
    Appointment updateAppointment(Long id, Appointment actualAppointment);
    Appointment setAppointmentStatus(Long id, AppointmentStatus status);
    void deleteAppointment(Long id);
}
