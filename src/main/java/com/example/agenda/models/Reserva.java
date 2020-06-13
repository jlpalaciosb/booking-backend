package com.example.agenda.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
public class Reserva {

    private @Id @GeneratedValue Long id;
    private @NotNull LocalDate fecha;
    private @NotNull LocalTime horaInicio;
    private @NotNull LocalTime horaFin;
    private @ManyToOne @NotNull Servicio servicio;
    private @ManyToOne @NotNull Profesional profesional;
    private @ManyToOne @NotNull Cliente cliente;
    private @NotNull Character estado;
    private String comentario;

    public Reserva() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva)) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(this.id, reserva.id) &&
                Objects.equals(this.fecha, reserva.fecha) &&
                Objects.equals(this.horaInicio, reserva.horaInicio) &&
                Objects.equals(this.horaFin, reserva.horaFin) &&
                Objects.equals(this.servicio, reserva.servicio) &&
                Objects.equals(this.profesional, reserva.profesional) &&
                Objects.equals(this.cliente, reserva.cliente) &&
                Objects.equals(this.estado, reserva.estado) &&
                Objects.equals(this.comentario, reserva.comentario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.fecha, this.horaInicio, this.horaFin, this.servicio, this.profesional,
                this.cliente, this.estado, this.comentario);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + this.id + ", " +
                "fecha='" + this.fecha.toString() + "', " +
                "servicio='" + this.servicio.getNombre() + "'}";
    }
}
