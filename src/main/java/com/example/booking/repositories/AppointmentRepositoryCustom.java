package com.example.booking.repositories;

import com.example.booking.models.Appointment;
import com.example.booking.models.Client;
import com.example.booking.models.Professional;
import com.example.booking.models.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepositoryCustom {
    List<Appointment> findAll(LocalDate minDate, LocalDate maxDate, Long clientId);
    boolean existsService(Service service);
    boolean existsClient(Client client);
    boolean existsProfessional(Professional professional);
    /** @param uaid Updating appointment id */
    boolean occupiedProfessional(Long uaid, Professional professional, LocalDate date,
                                 LocalTime startTime, LocalTime finishTime);
}
