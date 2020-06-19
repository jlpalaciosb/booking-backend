package com.example.booking.services;

import com.example.booking.models.Professional;
import com.example.booking.models.Service;
import java.util.List;
import java.util.Set;

public interface ProfessionalService {
    List<Professional> listProfessionals();
    Professional getProfessional(Long id);
    Professional createProfessional(Professional newProfessional);
    Professional updateProfessional(Long id, Professional actualProfessional);
    void deleteProfessional(Long id);
    Set<Service> listServices(Long id);
    void addServices(Long id, List<Service> services);
    void removeServices(Long id, List<Service> services);
}
