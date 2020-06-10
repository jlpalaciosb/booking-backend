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
    List<Reserva> all() {
        return repository.findAll();
    }

    @PostMapping("/reservas")
    Reserva newReserva(@RequestBody Reserva nuevoReserva) {
        return repository.save(nuevoReserva);
    }

    // Un ítem en específico

    @GetMapping("/reservas/{id}")
    Reserva one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("reserva", id));
    }

    @PutMapping("/reservas/{id}")
    Reserva replaceReserva(@RequestBody Reserva nuevaReserva, @PathVariable Long id) {
        return repository.findById(id)
                .map(reserva -> {
                    reserva.setFecha(nuevaReserva.getFecha());
                    reserva.setHoraInicio(nuevaReserva.getHoraInicio());
                    reserva.setHoraFin(nuevaReserva.getHoraFin());
                    reserva.setServicio(nuevaReserva.getServicio());
                    reserva.setProfesional(nuevaReserva.getProfesional());
                    reserva.setCliente(nuevaReserva.getCliente());
                    reserva.setEstado(nuevaReserva.getEstado());
                    reserva.setComentario(nuevaReserva.getComentario());
                    return repository.save(reserva);
                })
                .orElseThrow(() -> new EntityNotFoundException("reserva", id));
    }

    @DeleteMapping("/reservas/{id}")
    void deleteReserva(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("reserva", id);
        }
    }
}
