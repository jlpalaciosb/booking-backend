package com.example.booking.services;

import com.example.booking.models.Client;
import com.example.booking.repositories.ClientRepository;
import com.example.booking.services.errors.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepo;

    public ClientServiceImpl(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public List<Client> listClients() {
        return clientRepo.findAll();
    }

    @Override
    public Client getClient(Long id) {
        return clientRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("client", id));
    }

    @Override
    public Client createClient(Client newClient) {
        return clientRepo.save(newClient);
    }

    @Override
    public Client updateClient(Long id, Client actualClient) {
        return clientRepo.findById(id)
                .map(client -> {
                    client.setDocument(actualClient.getDocument());
                    client.setFirstName(actualClient.getFirstName());
                    client.setLastName(actualClient.getLastName());
                    client.setEmail(actualClient.getEmail());
                    client.setPhoneNumber(actualClient.getPhoneNumber());
                    client.setBirthdate(actualClient.getBirthdate());
                    return clientRepo.save(client);
                })
                .orElseThrow(() -> new NotFoundException("client", id));
    }

    @Override
    public void deleteClient(Long id) {
        if (clientRepo.existsById(id)) {
            clientRepo.deleteById(id);
        } else {
            throw new NotFoundException("client", id);
        }
    }
}
