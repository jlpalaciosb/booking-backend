package com.example.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booking.models.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    boolean existsByName(String name);
}
