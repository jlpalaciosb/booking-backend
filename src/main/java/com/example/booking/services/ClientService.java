package com.example.booking.services;

import com.example.booking.models.Client;
import org.springframework.data.domain.Page;

public interface ClientService {
    Page<Client> listClients(String filter, Integer page, Integer pageSize, String sortBy);
    Client getClient(Long id);
    Client createClient(Client newClient);
    Client updateClient(Long id, Client actualClient);
    void deleteClient(Long id);
}
