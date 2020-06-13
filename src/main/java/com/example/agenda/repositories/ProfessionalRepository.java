package com.example.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agenda.models.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
}
