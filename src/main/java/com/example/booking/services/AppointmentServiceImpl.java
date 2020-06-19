package com.example.booking.services;

import com.example.booking.models.Appointment;
import com.example.booking.repositories.AppointmentRepository;
import com.example.booking.services.errors.BadRequestException;
import com.example.booking.services.errors.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepo;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public List<Appointment> listAppointments() {
        return appointmentRepo.findAll();
    }

    @Override
    public Appointment getAppointment(Long id) {
        return appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException("appointment", id));
    }

    @Override
    public Appointment createAppointment(Appointment newAppointment) {
        newAppointment.setState('S');
        validate(null, newAppointment);
        return appointmentRepo.save(newAppointment);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment actualAppointment) {
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

    // oldAppointment == null ? creating : updating
    private void validate(Appointment oldAppointment, Appointment newAppointment) {
        if (newAppointment.getDate() == null) {
            throw new BadRequestException("Set appointment date");
        }
        if (newAppointment.getStartTime() == null) {
            throw new BadRequestException("Set appointment start time");
        }
        if (newAppointment.getFinishTime() == null) {
            throw new BadRequestException("Set appointment finish time");
        }
        if (newAppointment.getService() == null || newAppointment.getService().getId() == null) {
            throw new BadRequestException("Set appointment service (id)");
        }
        if (newAppointment.getClient() == null || newAppointment.getClient().getId() == null) {
            throw new BadRequestException("Set appointment client (id)");
        }
        if (newAppointment.getProfessional() == null || newAppointment.getProfessional().getId() == null) {
            throw new BadRequestException("Set appointment professional (id)");
        }
        Character nas = newAppointment.getState();
        if (nas == null || !(nas.equals('S') || nas.equals('Y') || nas.equals('N'))) {
            throw new BadRequestException("Set appointment state (S/Y/N)");
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
        Long uaid = oldAppointment != null ? oldAppointment.getId() : -1;
        if (appointmentRepo.occupiedProfessional(uaid, newAppointment.getProfessional(), newAppointment.getDate(),
                newAppointment.getStartTime(), newAppointment.getFinishTime())) {
            throw new BadRequestException("Professional has another appointment in the given time frame");
        }
    }
}
