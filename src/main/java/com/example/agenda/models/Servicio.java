package com.example.agenda.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Servicio {

    private @Id @GeneratedValue Long id;
    private String nombre;
    private String descripcion;

    public Servicio() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Servicio)) return false;
        Servicio servicio = (Servicio) o;
        return Objects.equals(this.id, servicio.id) &&
                Objects.equals(this.nombre, servicio.nombre) &&
                Objects.equals(this.descripcion, servicio.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nombre, this.descripcion);
    }

    @Override
    public String toString() {
        return "Servicio{" +
                "id=" + this.id + ", " +
                "nombre='" + this.nombre + "', " +
                "descripcion='" + this.descripcion + "'}";
    }
}
