package com.example.booking.repositories;

import com.example.booking.models.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booking.models.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Page<Client> findByLastNameIgnoreCaseStartsWith(String filter, Pageable pageable);

    boolean existsByDocument(String document);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    @Query("select (count(a) >= 1) from Appointment a where a.client = :c")
    boolean existsAppointmentWithClient(@Param("c") Client client);
}
