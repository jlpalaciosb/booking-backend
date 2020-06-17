package com.example.booking.repositories;

import com.example.booking.models.Service;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional
public class ServiceRepositoryCustomImpl implements ServiceRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public boolean existsAppointmentWithService(Service service) {
        Query q = em.createQuery(
                "select count(a) from Appointment a where a.service.id = :sid");
        q.setParameter("sid", service.getId());
        return (long) q.getSingleResult() >= 1;
    }

    @Override
    public void removeFromProfessionalsServices(Service service) {
        Query q = em.createNativeQuery(
                "delete from professional_services where services_id = ?1");
        q.setParameter(1, service.getId());
        q.executeUpdate();
    }
}
