package com.example.agenda.models;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Cliente extends Persona {

    public Cliente() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(this.id, cliente.id) &&
                Objects.equals(this.documento, cliente.documento) &&
                Objects.equals(this.nombre, cliente.nombre) &&
                Objects.equals(this.apellido, cliente.apellido) &&
                Objects.equals(this.correo, cliente.correo) &&
                Objects.equals(this.telefono, cliente.telefono) &&
                Objects.equals(this.fechaNacimiento, cliente.fechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.nombre, this.nombre, this.apellido, this.correo, this.telefono,
                this.fechaNacimiento);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + this.id + ", " +
                "nombre='" + this.nombre + "', " +
                "apellido='" + this.apellido + "'}";
    }
}
