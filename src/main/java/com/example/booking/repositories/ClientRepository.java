package com.example.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booking.models.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByDocument(String document);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    @Query("select (count(a) >= 1) from Appointment a where a.client = :c")
    boolean existsAppointmentWithClient(@Param("c") Client client);
}
