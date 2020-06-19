package com.example.booking.repositories;

import com.example.booking.models.Client;
import com.example.booking.models.Professional;
import com.example.booking.models.Service;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalTime;

@Repository
@Transactional(readOnly = true)
public class AppointmentRepositoryCustomImpl implements AppointmentRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public boolean existsService(Service service) {
        Query q = em.createQuery("select count(s) from Service s where s.id = :sid");
        q.setParameter("sid", service.getId());
        return (long) q.getSingleResult() >= 1;
    }

    @Override
    public boolean existsClient(Client client) {
        Query q = em.createQuery("select count(c) from Client c where c.id = :cid");
        q.setParameter("cid", client.getId());
        return (long) q.getSingleResult() >= 1;
    }

    @Override
    public boolean existsProfessional(Professional professional) {
        Query q = em.createQuery("select count(p) from Professional p where p.id = :pid");
        q.setParameter("pid", professional.getId());
        return (long) q.getSingleResult() >= 1;
    }

    @Override
    public boolean occupiedProfessional(Long uaid, Professional professional, LocalDate date, LocalTime startTime,
                                        LocalTime finishTime) {
        Query q = em.createQuery("select count(a) from Appointment a where a.id <> :uaid and " +
                "a.professional.id = :pid and a.date = :dt and a.finishTime > :st and a.startTime < :ft");
        q.setParameter("uaid", uaid);
        q.setParameter("pid", professional.getId());
        q.setParameter("dt", date);
        q.setParameter("st", startTime);
        q.setParameter("ft", finishTime);
        return (long) q.getSingleResult() >= 1;
    }
}
