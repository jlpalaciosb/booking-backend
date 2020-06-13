package com.example.agenda.controllers;

import java.util.List;
import com.example.agenda.services.AppointmentService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.agenda.models.Appointment;

@RestController
class ReservaController {

    private final AppointmentService appointmentService;

    ReservaController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/reservas")
    List<Appointment> listarReservas() {
        return appointmentService.listAppointments();
    }

    @GetMapping("/reservas/{id}")
    Appointment obtenerReserva(@PathVariable Long id) {
        return appointmentService.getAppointment(id);
    }

    @PostMapping("/reservas")
    Appointment crearReserva(@RequestBody Appointment newAppointment) {
        return appointmentService.createAppointment(newAppointment);
    }

    @PutMapping("/reservas/{id}")
    Appointment actualizarReserva(@PathVariable Long id, @RequestBody Appointment actualAppointment) {
        return appointmentService.updateAppointment(id, actualAppointment);
    }

    @DeleteMapping("/reservas/{id}")
    void eliminarReserva(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}
