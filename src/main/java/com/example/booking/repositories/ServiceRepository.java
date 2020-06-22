package com.example.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booking.models.Service;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    boolean existsByName(String name);

    @Query("select (count(a) >= 1) from Appointment a where a.service = :s")
    boolean existsAppointmentWithService(@Param("s") Service service);

    /* Remove the service from services lists of professionals offering the service */
    @Modifying @Transactional
    @Query(value = "delete from professional_services where services_id = :sid", nativeQuery = true)
    void removeFromProfessionalsServices(@Param("sid") Long serviceId);
}
