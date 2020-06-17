package com.example.booking.repositories;

import com.example.booking.models.Client;

public interface ClientRepositoryCustom {
    boolean existsAppointmentWithClient(Client client);
}
