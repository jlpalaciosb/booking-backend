package com.example.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agenda.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
