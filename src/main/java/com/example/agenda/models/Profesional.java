package com.example.agenda.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Profesional extends Persona {

    @ManyToMany
    private Set<Servicio> servicios = new TreeSet<>();

    public Profesional() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profesional)) return false;
        Profesional profesional = (Profesional) o;
        return Objects.equals(this.id, profesional.id) &&
                Objects.equals(this.documento, profesional.documento) &&
                Objects.equals(this.nombre, profesional.nombre) &&
                Objects.equals(this.apellido, profesional.apellido) &&
                Objects.equals(this.correo, profesional.correo) &&
                Objects.equals(this.telefono, profesional.telefono) &&
                Objects.equals(this.fechaNacimiento, profesional.fechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nombre, this.nombre, this.apellido, this.correo, this.telefono,
                this.fechaNacimiento);
    }

    @Override
    public String toString() {
        return "Profesional{" +
                "id=" + this.id + ", " +
                "nombre='" + this.nombre + "', " +
                "apellido='" + this.apellido + "'}";
    }

    public Set<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(Set<Servicio> servicios) {
        this.servicios = servicios;
    }
}
