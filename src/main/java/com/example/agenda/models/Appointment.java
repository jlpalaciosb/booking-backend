package com.example.agenda.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Appointment {

    private @Id @GeneratedValue Long id;
    private @NotNull LocalDate date;
    private @NotNull LocalTime startTime;
    private @NotNull LocalTime finishTime;
    private @ManyToOne @NotNull Service service;
    private @ManyToOne @NotNull Professional professional;
    private @ManyToOne @NotNull Client client;
    private @NotNull Character state;
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

    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
        return Objects.equals(this.id, appointment.id) &&
                Objects.equals(this.date, appointment.date) &&
                Objects.equals(this.startTime, appointment.startTime) &&
                Objects.equals(this.finishTime, appointment.finishTime) &&
                Objects.equals(this.service, appointment.service) &&
                Objects.equals(this.professional, appointment.professional) &&
                Objects.equals(this.client, appointment.client) &&
                Objects.equals(this.state, appointment.state) &&
                Objects.equals(this.comment, appointment.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.date, this.startTime, this.finishTime, this.service, this.professional,
                this.client, this.state, this.comment);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + this.id + ", " +
                "date='" + this.date.toString() + "', " +
                "service='" + this.service.getName() + "'}";
    }
}
