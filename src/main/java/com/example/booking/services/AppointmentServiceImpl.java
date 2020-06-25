package com.example.booking.services;

import com.example.booking.models.Appointment;
import com.example.booking.repositories.AppointmentRepository;
import com.example.booking.errors.BadRequestException;
import com.example.booking.errors.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepo;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public Page<Appointment> listAppointments(LocalDate minDate, LocalDate maxDate, Long clientId,
                                              Integer page, Integer pageSize) {
        Specification<Appointment> s1 = (root, query, cb) ->
                minDate == null ? null : cb.greaterThanOrEqualTo(root.get("date"), minDate);
        Specification<Appointment> s2 = (root, query, cb) ->
                maxDate == null ? null : cb.lessThanOrEqualTo(root.get("date"), maxDate);
        Specification<Appointment> s3 = (root, query, cb) ->
                clientId == null ? null : cb.equal(root.get("client").get("id"), clientId);
        Specification<Appointment> spec = Specification.where(s1).and(s2).and(s3);

        page = Math.max(0, page);
        pageSize = Math.min(Math.max(1, pageSize), 100);
        Sort sort = Sort.by("date").descending().and(Sort.by("startTime").ascending());
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        return appointmentRepo.findAll(spec, pageable);
    }

    @Override
    public Appointment getAppointment(Long id) {
        return appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException("appointment", id));
    }

    @Override
    public Appointment createAppointment(Appointment newAppointment) {
        newAppointment.trim();
        validate(null, newAppointment);
        return appointmentRepo.save(newAppointment);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment actualAppointment) {
        actualAppointment.trim();
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("appointment", id));
        validate(appointment, actualAppointment);
        appointment.setDate(actualAppointment.getDate());
        appointment.setStartTime(actualAppointment.getStartTime());
        appointment.setFinishTime(actualAppointment.getFinishTime());
        appointment.setService(actualAppointment.getService());
        appointment.setProfessional(actualAppointment.getProfessional());
        appointment.setClient(actualAppointment.getClient());
        appointment.setState(actualAppointment.getState());
        appointment.setComment(actualAppointment.getComment());
        return appointmentRepo.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        if (appointmentRepo.existsById(id)) {
            appointmentRepo.deleteById(id);
        } else {
            throw new NotFoundException("appointment", id);
        }
    }

    private void validate(Appointment oldAppointment, Appointment newAppointment) {
        boolean creating = (oldAppointment == null);
        if (newAppointment.getService().getId() == null) {
            throw new BadRequestException("Set service id");
        }
        if (newAppointment.getClient().getId() == null) {
            throw new BadRequestException("Set client id");
        }
        if (newAppointment.getProfessional().getId() == null) {
            throw new BadRequestException("Set professional id");
        }
        Character nas = newAppointment.getState();
        if (!(nas.equals('S') || nas.equals('Y') || nas.equals('N'))) {
            throw new BadRequestException("Set a correct state (S/Y/N)");
        }
        if (!(newAppointment.getStartTime().compareTo(newAppointment.getFinishTime()) < 0)) {
            throw new BadRequestException("Start time must be less than finish time");
        }
        if (!appointmentRepo.existsService(newAppointment.getService())) {
            throw new BadRequestException("Unable to find service with id " + newAppointment.getService().getId());
        }
        if (!appointmentRepo.existsClient(newAppointment.getClient())) {
            throw new BadRequestException("Unable to find client with id " + newAppointment.getClient().getId());
        }
        if (!appointmentRepo.existsProfessional(newAppointment.getProfessional())) {
            throw new BadRequestException("Unable to find professional with id " +
                    newAppointment.getProfessional().getId());
        }
        Long uaid = creating ? -1 : oldAppointment.getId();
        if (appointmentRepo.occupiedProfessional(uaid, newAppointment.getProfessional(), newAppointment.getDate(),
                newAppointment.getStartTime(), newAppointment.getFinishTime())) {
            throw new BadRequestException("Professional has another appointment in the given time frame");
        }
    }
}
