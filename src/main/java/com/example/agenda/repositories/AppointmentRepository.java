package com.example.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agenda.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
