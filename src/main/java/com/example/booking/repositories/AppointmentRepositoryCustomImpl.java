package com.example.booking.repositories;

import com.example.booking.models.Appointment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class AppointmentRepositoryCustomImpl implements AppointmentRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Appointment> findAll(LocalDate minDate, LocalDate maxDate, Long clientId) {
        boolean f = false;
        String dynamicQueryStr = "select a from Appointment a";
        if (minDate != null) {
            f = true;
            dynamicQueryStr += " where a.date >= :minDate ";
        }
        if (maxDate != null) {
            dynamicQueryStr += f ? " and " : " where ";
            f = true;
            dynamicQueryStr += " a.date <= :maxDate ";
        }
        if (clientId != null) {
            dynamicQueryStr += f ? " and " : " where ";
            dynamicQueryStr += " a.client.id = :clientId ";
        }

        Query q = em.createQuery(dynamicQueryStr);
        if (minDate != null) {
            q.setParameter("minDate", minDate);
        }
        if (maxDate != null) {
            q.setParameter("maxDate", maxDate);
        }
        if (clientId != null) {
            q.setParameter("clientId", clientId);
        }

        return (List<Appointment>) q.getResultList();
    }
}
