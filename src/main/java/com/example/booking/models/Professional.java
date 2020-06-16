package com.example.booking.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Professional extends Person {

    private @ManyToMany Set<Service> services = new TreeSet<>();

    public Professional() {}

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professional)) return false;
        Professional professional = (Professional) o;
        return Objects.equals(this.id, professional.id) &&
                Objects.equals(this.document, professional.document) &&
                Objects.equals(this.firstName, professional.firstName) &&
                Objects.equals(this.lastName, professional.lastName) &&
                Objects.equals(this.email, professional.email) &&
                Objects.equals(this.phoneNumber, professional.phoneNumber) &&
                Objects.equals(this.birthdate, professional.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.firstName, this.lastName, this.email, this.phoneNumber,
                this.birthdate);
    }

    @Override
    public String toString() {
        return "Professional{" +
                "id=" + this.id + ", " +
                "firstName='" + this.firstName + "', " +
                "lastName='" + this.lastName + "'}";
    }
}
