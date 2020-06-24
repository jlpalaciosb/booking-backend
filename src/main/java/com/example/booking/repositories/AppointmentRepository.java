package com.example.booking.repositories;

import com.example.booking.models.Client;
import com.example.booking.models.Professional;
import com.example.booking.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booking.models.Appointment;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.time.LocalTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {

    @Query("select (count(s) >= 1) from Service s where s = :s")
    boolean existsService(@Param("s") Service service);

    @Query("select (count(c) >= 1) from Client c where c = :c")
    boolean existsClient(@Param("c") Client client);

    @Query("select (count(p) >= 1) from Professional p where p = :p")
    boolean existsProfessional(@Param("p") Professional professional);

    /** @param uaid Updating appointment id */
    @Query("select (count(a) >= 1) from Appointment a where a.id <> :uaid and " +
           "a.professional = :p and a.date = :dt and a.finishTime > :st and a.startTime < :ft")
    boolean occupiedProfessional(@Param("uaid") Long uaid, @Param("p") Professional professional,
                                 @Param("dt") LocalDate date, @Param("st") LocalTime startTime,
                                 @Param("ft") LocalTime finishTime);
}
