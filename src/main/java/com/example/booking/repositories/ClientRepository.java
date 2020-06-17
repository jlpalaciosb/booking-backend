package com.example.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booking.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long>, ClientRepositoryCustom {
    boolean existsByDocument(String document);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
}
