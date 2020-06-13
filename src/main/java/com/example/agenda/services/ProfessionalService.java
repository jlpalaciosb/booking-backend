package com.example.agenda.services;

import com.example.agenda.models.Professional;
import java.util.List;

public interface ProfessionalService {
    List<Professional> listProfessionals();
    Professional getProfessional(Long id);
    Professional createProfessional(Professional newProfessional);
    Professional updateProfessional(Long id, Professional actualProfessional);
    void deleteProfessional(Long id);
}
