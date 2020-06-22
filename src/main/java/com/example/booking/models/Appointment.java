package com.example.booking.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Appointment {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "Date is mandatory")
    private LocalDate date; // TODO: apply index

    @NotNull(message = "Start time is mandatory")
    private LocalTime startTime;

    @NotNull(message = "Finish time is mandatory")
    private LocalTime finishTime;

    @NotNull(message = "Service is mandatory")
    @ManyToOne(optional = false)
    private Service service;

    @NotNull(message = "Client is mandatory")
    @ManyToOne(optional = false)
    private Client client;

    @NotNull(message = "Professional is mandatory")
    @ManyToOne(optional = false)
    private Professional professional;

    // Valid States: S (scheduled), Y (attended), N (did not attend)
    @NotNull(message = "State is mandatory")
    private Character state;

    private String comment;

    public Appointment() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment appointment = (Appointment) o;
        return Objects.equals(this.id, appointment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + this.id + ", " +
                "date='" + this.date.toString() + "', " +
                "service='" + this.service.getName() + "'}";
    }
}
