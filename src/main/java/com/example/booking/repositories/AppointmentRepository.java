package com.example.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booking.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>, AppointmentRepositoryCustom {
}
