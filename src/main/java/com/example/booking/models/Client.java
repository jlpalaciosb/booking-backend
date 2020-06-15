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
        return Objects.equals(this.id, client.id) &&
                Objects.equals(this.document, client.document) &&
                Objects.equals(this.firstName, client.firstName) &&
                Objects.equals(this.lastName, client.lastName) &&
                Objects.equals(this.email, client.email) &&
                Objects.equals(this.phoneNumber, client.phoneNumber) &&
                Objects.equals(this.birthdate, client.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.firstName, this.lastName, this.email, this.phoneNumber,
                this.birthdate);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + this.id + ", " +
                "firstName='" + this.firstName + "', " +
                "lastName='" + this.lastName + "'}";
    }
}
