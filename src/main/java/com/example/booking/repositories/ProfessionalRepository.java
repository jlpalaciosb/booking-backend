package com.example.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booking.models.Professional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

    boolean existsByDocument(String document);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    @Query("select (count(a) >= 1) from Appointment a where a.professional = :p")
    boolean existsAppointmentWithProfessional(@Param("p") Professional professional);

    @Query("select (count(s) >= 1) from Service s where s.id = :sid")
    boolean existsServiceWithId(@Param("sid") Long sid);
}
