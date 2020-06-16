package com.example.booking.services;

import com.example.booking.models.Appointment;
import com.example.booking.repositories.AppointmentRepository;
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
        return appointmentRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("appointment", id));
    }

    @Override
    public Appointment createAppointment(Appointment newAppointment) {
        return appointmentRepo.save(newAppointment);
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment actualAppointment) {
        return appointmentRepo.findById(id)
                .map(appointment -> {
                    appointment.setDate(actualAppointment.getDate());
                    appointment.setStartTime(actualAppointment.getStartTime());
                    appointment.setFinishTime(actualAppointment.getFinishTime());
                    appointment.setService(actualAppointment.getService());
                    appointment.setProfessional(actualAppointment.getProfessional());
                    appointment.setClient(actualAppointment.getClient());
                    appointment.setState(actualAppointment.getState());
                    appointment.setComment(actualAppointment.getComment());
                    return appointmentRepo.save(appointment);
                })
                .orElseThrow(() -> new NotFoundException("appointment", id));
    }

    @Override
    public void deleteAppointment(Long id) {
        if (appointmentRepo.existsById(id)) {
            appointmentRepo.deleteById(id);
        } else {
            throw new NotFoundException("appointment", id);
        }
    }
}
