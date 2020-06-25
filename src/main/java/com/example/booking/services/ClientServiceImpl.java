package com.example.booking.services;

import com.example.booking.models.Client;
import com.example.booking.repositories.ClientRepository;
import com.example.booking.errors.BadRequestException;
import com.example.booking.errors.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepo;

    public ClientServiceImpl(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public Page<Client> listClients(String filter, Integer page, Integer pageSize, String sortBy) {
        page = Math.max(0, page);
        pageSize = Math.min(Math.max(1, pageSize), 100);

        Sort sort = Sort.by("lastName").ascending();
        if ("-lastName".equals(sortBy)) sort = Sort.by("lastName").descending();
        else if ("+firstName".equals(sortBy)) sort = Sort.by("firstName").ascending();
        else if ("-firstName".equals(sortBy)) sort = Sort.by("firstName").descending();
        else if ("+document".equals(sortBy)) sort = Sort.by("document").ascending();
        else if ("-document".equals(sortBy)) sort = Sort.by("document").descending();

        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return clientRepo.findByLastNameIgnoreCaseStartsWith(filter.trim(), pageable);
    }

    @Override
    public Client getClient(Long id) {
        return clientRepo.findById(id).orElseThrow(() -> new NotFoundException("client", id));
    }

    @Override
    public Client createClient(Client newClient) {
        newClient.trim();
        validate(null, newClient);
        return clientRepo.save(newClient);
    }

    @Override
    public Client updateClient(Long id, Client actualClient) {
        actualClient.trim();
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

    private void validate(Client oldClient, Client newClient) {
        boolean creating = (oldClient == null);
        if ((creating || !oldClient.getDocument().equals(newClient.getDocument())) &&
                clientRepo.existsByDocument(newClient.getDocument())) {
            throw new BadRequestException("There is another client with document = " + newClient.getDocument());
        }
        if ((creating || !oldClient.getEmail().equals(newClient.getEmail())) &&
                clientRepo.existsByEmail(newClient.getEmail())) {
            throw new BadRequestException("There is another client with email = " + newClient.getEmail());
        }
        if ((creating || !oldClient.getPhoneNumber().equals(newClient.getPhoneNumber())) &&
                clientRepo.existsByPhoneNumber(newClient.getPhoneNumber())) {
            throw new BadRequestException("There is another client with phone number = " + newClient.getPhoneNumber());
        }
    }
}
