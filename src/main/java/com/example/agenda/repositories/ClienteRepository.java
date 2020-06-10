package com.example.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.agenda.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
