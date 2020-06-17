package com.example.booking.repositories;

import com.example.booking.models.Professional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional
public class ProfessionalRepositoryCustomImpl implements ProfessionalRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public boolean existsAppointmentWithProfessional(Professional professional) {
        Query q = em.createQuery(
                "select count(a) from Appointment a where a.professional.id = :pid");
        q.setParameter("pid", professional.getId());
        return (long) q.getSingleResult() >= 1;
    }
}
