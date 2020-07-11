package com.example.booking.models;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Appointment {

    @Id
    @GeneratedValue
    @ApiModelProperty(position = 0)
    private Long id;

    @NotNull(message = "Date is mandatory")
    @ApiModelProperty(position = 1)
    private LocalDate date;

    @NotNull(message = "Start time is mandatory")
    @ApiModelProperty(position = 2)
    private LocalTime startTime;

    @NotNull(message = "Finish time is mandatory")
    @ApiModelProperty(position = 3)
    private LocalTime finishTime;

    @NotNull(message = "Service is mandatory")
    @ApiModelProperty(position = 4)
    @ManyToOne(optional = false)
    private Service service;

    @NotNull(message = "Client is mandatory")
    @ApiModelProperty(position = 5)
    @ManyToOne(optional = false)
    private Client client;

    @NotNull(message = "Professional is mandatory")
    @ApiModelProperty(position = 6)
    @ManyToOne(optional = false)
    private Professional professional;

    @ApiModelProperty(position = 7)
    private AppointmentStatus status;
    public enum AppointmentStatus { SCHEDULED, ATTENDED, NOT_ATTENDED, CANCELLED }

    @ApiModelProperty(position = 8)
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

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus state) {
        this.status = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void trim() {
        if (comment != null) comment = comment.trim();
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
