package com.example.agenda.controllers;

import java.util.List;
import com.example.agenda.services.ReservaService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.agenda.models.Reserva;

@RestController
class ReservaController {

    private final ReservaService reservaService;

    ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/reservas")
    List<Reserva> listarReservas() {
        return reservaService.listarReservas();
    }

    @GetMapping("/reservas/{id}")
    Reserva obtenerReserva(@PathVariable Long id) {
        return reservaService.obtenerReserva(id);
    }

    @PostMapping("/reservas")
    Reserva crearReserva(@RequestBody Reserva nuevoReserva) {
        return reservaService.crearReserva(nuevoReserva);
    }

    @PutMapping("/reservas/{id}")
    Reserva actualizarReserva(@PathVariable Long id, @RequestBody Reserva actualReserva) {
        return reservaService.actualizarReserva(id, actualReserva);
    }

    @DeleteMapping("/reservas/{id}")
    void eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
    }
}
