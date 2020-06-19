package com.example.booking.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@MappedSuperclass
abstract class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Document is mandatory")
    private String document;

    @Column(nullable = false)
    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email address")
    private String email;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    private LocalDate birthdate;

    public Person() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
