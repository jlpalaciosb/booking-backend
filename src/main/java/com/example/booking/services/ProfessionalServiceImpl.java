package com.example.booking.services;

import com.example.booking.models.Professional;
import com.example.booking.models.Service;
import com.example.booking.repositories.ProfessionalRepository;
import com.example.booking.services.errors.BadRequestException;
import com.example.booking.services.errors.NotFoundException;
import java.util.List;
import java.util.Set;

@org.springframework.stereotype.Service
public class ProfessionalServiceImpl implements ProfessionalService {

    private final ProfessionalRepository professionalRepo;

    public ProfessionalServiceImpl(ProfessionalRepository professionalRepo) {
        this.professionalRepo = professionalRepo;
    }

    @Override
    public List<Professional> listProfessionals() {
        return professionalRepo.findAll();
    }

    @Override
    public Professional getProfessional(Long id) {
        return professionalRepo.findById(id).orElseThrow(() -> new NotFoundException("professional", id));
    }

    @Override
    public Professional createProfessional(Professional newProfessional) {
        validate(null, newProfessional);
        return professionalRepo.save(newProfessional);
    }

    @Override
    public Professional updateProfessional(Long id, Professional actualProfessional) {
        Professional professional = professionalRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("professional", id));
        validate(professional, actualProfessional);
        professional.setDocument(actualProfessional.getDocument());
        professional.setFirstName(actualProfessional.getFirstName());
        professional.setLastName(actualProfessional.getLastName());
        professional.setEmail(actualProfessional.getEmail());
        professional.setPhoneNumber(actualProfessional.getPhoneNumber());
        professional.setBirthdate(actualProfessional.getBirthdate());
        return professionalRepo.save(professional);
    }

    @Override
    public void deleteProfessional(Long id) {
        Professional professional = professionalRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("professional", id));
        if (professionalRepo.existsAppointmentWithProfessional(professional)) {
            throw new BadRequestException("There is/are appointment/s associated with this professional");
        } else {
            professionalRepo.delete(professional);
        }
    }

    @Override
    public Set<Service> listServices(Long id) {
        Professional professional = professionalRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("professional", id));
        return professional.getServices();
    }

    @Override
    public void addServices(Long id, List<Service> services) {
        Professional professional = professionalRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("professional", id));

        // some validation
        for (Service s : services) {
            if (s.getId() == null) {
                throw new BadRequestException("Set service id");
            } else if (!professionalRepo.existsServiceWithId(s.getId())) {
                throw new BadRequestException("Unable to find service with id " + s.getId());
            }
        }

        professional.getServices().addAll(services);
        professionalRepo.save(professional);
    }

    @Override
    public void removeServices(Long id, List<Service> services) {
        Professional professional = professionalRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("professional", id));
        professional.getServices().removeAll(services);
        professionalRepo.save(professional);
    }

    // oldProfessional == null ? creating : updating
    private void validate(Professional oldProfessional, Professional newProfessional) {
        if ((oldProfessional == null || !oldProfessional.getDocument().equals(newProfessional.getDocument())) &&
                professionalRepo.existsByDocument(newProfessional.getDocument())) {
            throw new BadRequestException("There is another professional with document = " + newProfessional.getDocument());
        }
        if ((oldProfessional == null || !oldProfessional.getEmail().equals(newProfessional.getEmail())) &&
                professionalRepo.existsByEmail(newProfessional.getEmail())) {
            throw new BadRequestException("There is another professional with email = " + newProfessional.getEmail());
        }
        if ((oldProfessional == null || !oldProfessional.getPhoneNumber().equals(newProfessional.getPhoneNumber())) &&
                professionalRepo.existsByPhoneNumber(newProfessional.getPhoneNumber())) {
            throw new BadRequestException("There is another professional with phone number = " + newProfessional.getPhoneNumber());
        }
    }
}
