package com.example.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booking.models.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long>, ProfessionalRepositoryCustom {
    boolean existsByDocument(String document);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
