package com.example.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agenda.models.Profesional;

public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
}
