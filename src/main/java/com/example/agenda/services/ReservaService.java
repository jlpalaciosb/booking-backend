package com.example.agenda.services;

import com.example.agenda.models.Reserva;
import java.util.List;

public interface ReservaService {
    List<Reserva> listarReservas();
    Reserva obtenerReserva(Long id);
    Reserva crearReserva(Reserva nuevaReserva);
    Reserva actualizarReserva(Long id, Reserva actualReserva);
    void eliminarReserva(Long id);
}
