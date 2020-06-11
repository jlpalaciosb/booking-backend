package com.example.agenda.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.agenda.models.Reserva;
import com.example.agenda.repositories.ReservaRepository;

@RestController
class ReservaController {

    private final ReservaRepository repository;

    ReservaController(ReservaRepository repository) {
        this.repository = repository;
    }

    // Raíz de agregación

    @GetMapping("/reservas")
    List<Reserva> listarReservas() {
        return repository.findAll();
    }

    @PostMapping("/reservas")
    Reserva crearReserva(@RequestBody Reserva nuevaReserva) {
        return repository.save(nuevaReserva);
    }

    // Un ítem en específico

    @GetMapping("/reservas/{id}")
    Reserva obtenerReserva(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("reserva", id));
    }

    @PutMapping("/reservas/{id}")
    Reserva actualizarReserva(@PathVariable Long id, @RequestBody Reserva actualReserva) {
        return repository.findById(id)
                .map(reserva -> {
                    reserva.setFecha(actualReserva.getFecha());
                    reserva.setHoraInicio(actualReserva.getHoraInicio());
                    reserva.setHoraFin(actualReserva.getHoraFin());
                    reserva.setServicio(actualReserva.getServicio());
                    reserva.setProfesional(actualReserva.getProfesional());
                    reserva.setCliente(actualReserva.getCliente());
                    reserva.setEstado(actualReserva.getEstado());
                    reserva.setComentario(actualReserva.getComentario());
                    return repository.save(reserva);
                })
                .orElseThrow(() -> new EntityNotFoundException("reserva", id));
    }

    @DeleteMapping("/reservas/{id}")
    void eliminarReserva(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("reserva", id);
        }
    }
}
