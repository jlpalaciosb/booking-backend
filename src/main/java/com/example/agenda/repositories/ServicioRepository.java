package com.example.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.agenda.models.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
}
