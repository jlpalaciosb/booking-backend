package com.example.booking.services;

import com.example.booking.models.Client;
import com.example.booking.repositories.ClientRepository;
import com.example.booking.services.errors.BadRequestException;
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
        return clientRepo.findById(id).orElseThrow(() -> new NotFoundException("client", id));
    }

    @Override
    public Client createClient(Client newClient) {
        validate(null, newClient);
        return clientRepo.save(newClient);
    }

    @Override
    public Client updateClient(Long id, Client actualClient) {
        Client client = clientRepo.findById(id).orElseThrow(() -> new NotFoundException("client", id));
        validate(client, actualClient);
        client.setDocument(actualClient.getDocument());
        client.setFirstName(actualClient.getFirstName());
        client.setLastName(actualClient.getLastName());
        client.setEmail(actualClient.getEmail());
        client.setPhoneNumber(actualClient.getPhoneNumber());
        client.setBirthdate(actualClient.getBirthdate());
        return clientRepo.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        Client client = clientRepo.findById(id).orElseThrow(() -> new NotFoundException("client", id));
        if (clientRepo.existsAppointmentWithClient(client)) {
            throw new BadRequestException("There is/are appointment/s associated with this client");
        } else {
            clientRepo.delete(client);
        }
    }

    // oldClient == null ? creating : updating
    private void validate(Client oldClient, Client newClient) {
        if ((oldClient == null || !oldClient.getDocument().equals(newClient.getDocument())) &&
                clientRepo.existsByDocument(newClient.getDocument())) {
            throw new BadRequestException("There is another client with document = " + newClient.getDocument());
        }
        if ((oldClient == null || !oldClient.getEmail().equals(newClient.getEmail())) &&
                clientRepo.existsByEmail(newClient.getEmail())) {
            throw new BadRequestException("There is another client with email = " + newClient.getEmail());
        }
        if ((oldClient == null || !oldClient.getPhoneNumber().equals(newClient.getPhoneNumber())) &&
                clientRepo.existsByPhoneNumber(newClient.getPhoneNumber())) {
            throw new BadRequestException("There is another client with phone number = " + newClient.getPhoneNumber());
        }
    }
}
