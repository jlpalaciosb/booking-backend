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
        return Objects.equals(this.id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + this.id + ", " +
                "firstName='" + this.firstName + "', " +
                "lastName='" + this.lastName + "'}";
    }
}
