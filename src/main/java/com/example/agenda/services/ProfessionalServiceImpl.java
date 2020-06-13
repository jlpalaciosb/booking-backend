package com.example.agenda.services;

import com.example.agenda.models.Professional;
import com.example.agenda.repositories.ProfessionalRepository;
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
                .orElseThrow(() -> new EntityNotFoundException("professional", id));
    }

    @Override
    public Professional createProfessional(Professional newProfessional) {
        return professionalRepo.save(newProfessional);
    }

    @Override
    public Professional updateProfessional(Long id, Professional actualProfessional) {
        return professionalRepo.findById(id)
                .map(profesional -> {
                    profesional.setDocument(actualProfessional.getDocument());
                    profesional.setFirstName(actualProfessional.getFirstName());
                    profesional.setLastName(actualProfessional.getLastName());
                    profesional.setEmail(actualProfessional.getEmail());
                    profesional.setPhoneNumber(actualProfessional.getPhoneNumber());
                    profesional.setBirthdate(actualProfessional.getBirthdate());
                    return professionalRepo.save(profesional);
                })
                .orElseThrow(() -> new EntityNotFoundException("professional", id));
    }

    @Override
    public void deleteProfessional(Long id) {
        if (professionalRepo.existsById(id)) {
            professionalRepo.deleteById(id);
        } else {
            throw new EntityNotFoundException("professional", id);
        }
    }
}
