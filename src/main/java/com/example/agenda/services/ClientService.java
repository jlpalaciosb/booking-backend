package com.example.agenda.services;

import com.example.agenda.models.Client;
import java.util.List;

public interface ClientService {
    List<Client> listClients();
    Client getClient(Long id);
    Client createClient(Client newClient);
    Client updateClient(Long id, Client actualClient);
    void deleteClient(Long id);
}
