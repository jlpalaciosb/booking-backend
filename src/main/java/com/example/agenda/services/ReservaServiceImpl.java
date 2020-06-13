package com.example.agenda.services;

import com.example.agenda.models.Reserva;
import com.example.agenda.repositories.ReservaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepo;

    public ReservaServiceImpl(ReservaRepository reservaRepo) {
        this.reservaRepo = reservaRepo;
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepo.findAll();
    }

    @Override
    public Reserva obtenerReserva(Long id) {
        return reservaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("reserva", id));
    }

    @Override
    public Reserva crearReserva(Reserva nuevoReserva) {
        return reservaRepo.save(nuevoReserva);
    }

    @Override
    public Reserva actualizarReserva(Long id, Reserva actualReserva) {
        return reservaRepo.findById(id)
                .map(reserva -> {
                    reserva.setFecha(actualReserva.getFecha());
                    reserva.setHoraInicio(actualReserva.getHoraInicio());
                    reserva.setHoraFin(actualReserva.getHoraFin());
                    reserva.setServicio(actualReserva.getServicio());
                    reserva.setProfesional(actualReserva.getProfesional());
                    reserva.setCliente(actualReserva.getCliente());
                    reserva.setEstado(actualReserva.getEstado());
                    reserva.setComentario(actualReserva.getComentario());
                    return reservaRepo.save(reserva);
                })
                .orElseThrow(() -> new EntityNotFoundException("reserva", id));
    }

    @Override
    public void eliminarReserva(Long id) {
        if (reservaRepo.existsById(id)) {
            reservaRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("reserva", id);
        }
    }
}
