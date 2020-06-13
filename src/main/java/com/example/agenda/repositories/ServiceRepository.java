package com.example.agenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.agenda.models.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
