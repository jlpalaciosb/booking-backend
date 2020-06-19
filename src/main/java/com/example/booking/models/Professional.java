package com.example.booking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Professional extends Person {

    @ManyToMany
    @JsonIgnore
    private Set<Service> services = new TreeSet<>();

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
        return Objects.equals(this.getId(), professional.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public String toString() {
        return "Professional{" +
                "id=" + this.getId() + ", " +
                "firstName='" + this.getFirstName() + "', " +
                "lastName='" + this.getLastName() + "'}";
    }
}
