package com.example.booking.repositories;

import com.example.booking.models.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
@Transactional
public class ClientRepositoryCustomImpl implements ClientRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public boolean existsAppointmentWithClient(Client client) {
        Query q = em.createQuery(
                "select count(a) from Appointment a where a.client.id = :cid");
        q.setParameter("cid", client.getId());
        return (long) q.getSingleResult() >= 1;
    }
}
