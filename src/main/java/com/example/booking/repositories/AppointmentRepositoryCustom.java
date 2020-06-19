package com.example.booking.repositories;

import com.example.booking.models.Client;
import com.example.booking.models.Professional;
import com.example.booking.models.Service;
import java.time.LocalDate;
import java.time.LocalTime;

public interface AppointmentRepositoryCustom {
    boolean existsService(Service service);
    boolean existsClient(Client client);
    boolean existsProfessional(Professional professional);
    // uaid: updating appointment id
    boolean occupiedProfessional(Long uaid, Professional professional, LocalDate date,
                                 LocalTime startTime, LocalTime finishTime);
}
