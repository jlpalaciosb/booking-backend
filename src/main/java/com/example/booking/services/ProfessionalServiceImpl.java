package com.example.booking.services;

import com.example.booking.models.Professional;
import com.example.booking.repositories.ProfessionalRepository;
import com.example.booking.services.errors.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
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
        return professionalRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("professional", id));
    }

    @Override
    public Professional createProfessional(Professional newProfessional) {
        return professionalRepo.save(newProfessional);
    }

    @Override
    public Professional updateProfessional(Long id, Professional actualProfessional) {
        return professionalRepo.findById(id)
                .map(professional -> {
                    professional.setDocument(actualProfessional.getDocument());
                    professional.setFirstName(actualProfessional.getFirstName());
                    professional.setLastName(actualProfessional.getLastName());
                    professional.setEmail(actualProfessional.getEmail());
                    professional.setPhoneNumber(actualProfessional.getPhoneNumber());
                    professional.setBirthdate(actualProfessional.getBirthdate());
                    return professionalRepo.save(professional);
                })
                .orElseThrow(() -> new NotFoundException("professional", id));
    }

    @Override
    public void deleteProfessional(Long id) {
        if (professionalRepo.existsById(id)) {
            professionalRepo.deleteById(id);
        } else {
            throw new NotFoundException("professional", id);
        }
    }
}
