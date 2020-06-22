package com.example.booking.controllers;

import java.time.LocalDate;
import java.util.List;
import com.example.booking.services.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.booking.models.Appointment;
import javax.validation.Valid;

@RestController
class AppointmentController {

    private final AppointmentService appointmentService;

    AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointments")
    List<Appointment> listAppointments(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate minDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate maxDate,
            @RequestParam(required = false) Long clientId) {
        return appointmentService.listAppointments(minDate, maxDate, clientId);
    }

    @GetMapping("/appointments/{id}")
    Appointment getAppointment(@PathVariable Long id) {
        return appointmentService.getAppointment(id);
    }

    @PostMapping("/appointments")
    Appointment createAppointment(@RequestBody @Valid Appointment newAppointment) {
        return appointmentService.createAppointment(newAppointment);
    }

    @PutMapping("/appointments/{id}")
    Appointment updateAppointment(@PathVariable Long id, @RequestBody @Valid Appointment actualAppointment) {
        return appointmentService.updateAppointment(id, actualAppointment);
    }

    @DeleteMapping("/appointments/{id}")
    void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}
