package com.example.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agenda.models.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
