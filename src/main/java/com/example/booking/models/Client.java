package com.example.booking.models;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Client extends Person {

    public Client() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(this.getId(), client.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + this.getId() + ", " +
                "firstName='" + this.getFirstName() + "', " +
                "lastName='" + this.getLastName() + "'}";
    }
}
