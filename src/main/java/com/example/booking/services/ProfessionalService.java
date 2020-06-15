package com.example.booking.services;

import com.example.booking.models.Professional;
import java.util.List;

public interface ProfessionalService {
    List<Professional> listProfessionals();
    Professional getProfessional(Long id);
    Professional createProfessional(Professional newProfessional);
    Professional updateProfessional(Long id, Professional actualProfessional);
    void deleteProfessional(Long id);
}
