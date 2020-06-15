package com.example.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booking.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
