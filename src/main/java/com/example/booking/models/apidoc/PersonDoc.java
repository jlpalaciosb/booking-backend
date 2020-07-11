package com.example.booking.models.apidoc;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;

public class PersonDoc {

    @ApiModelProperty(position = 0)
    private String document;

    @ApiModelProperty(position = 1)
    private String firstName;

    @ApiModelProperty(position = 2)
    private String lastName;

    @ApiModelProperty(position = 3)
    private String email;

    @ApiModelProperty(position = 4)
    private String phoneNumber;

    @ApiModelProperty(position = 5)
    private LocalDate birthdate;

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

    public static abstract class ClientPost extends PersonDoc {}

    public static abstract class ClientPut extends PersonDoc {}

    public static abstract class ProfessionalPost extends PersonDoc {}

    public static abstract class ProfessionalPut extends PersonDoc {}

    public static abstract class ProfessionalServicePostDelete {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    public static abstract class ProfessionalServicePost extends ProfessionalServicePostDelete {}

    public static abstract class ProfessionalServiceDelete extends ProfessionalServicePostDelete {}
}