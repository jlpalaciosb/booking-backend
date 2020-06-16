package com.example.booking.models;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Service implements Comparable<Service> {

    private @Id @GeneratedValue Long id;
    private @Column(nullable = false, unique = true) String name;
    private String description;

    public Service() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return Objects.equals(this.id, service.id) &&
                Objects.equals(this.name, service.name) &&
                Objects.equals(this.description, service.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.description);
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + this.id + ", " +
                "name='" + this.name + "', " +
                "description='" + this.description + "'}";
    }

    @Override
    public int compareTo(Service service) {
        return this.name.compareTo(service.name);
    }
}
