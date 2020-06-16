package com.example.booking.repositories;

import com.example.booking.models.Appointment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(readOnly = true)
public class AppointmentRespositoryCustomImpl implements AppointmentRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Appointment getByCriteria(String criteria) {
        /*Query query = entityManager.createNativeQuery("SELECT em.* FROM spring_data_jpa_example.employee as em " +
                "WHERE em.firstname LIKE ?", Appointment.class);
        query.setParameter(1, firstName + "%");
        return (Service) query.getSingleResult();*/
        return null;
    }
}
