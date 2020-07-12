package com.example.booking.models.apidoc;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;

public class PersonDoc {

    @ApiModelProperty(position = 0, example = "1234567890")
    private String document;

    @ApiModelProperty(position = 1, example = "John")
    private String firstName;

    @ApiModelProperty(position = 2, example = "Doe")
    private String lastName;

    @ApiModelProperty(position = 3, example = "johndoe@example.com")
    private String email;

    @ApiModelProperty(position = 4, example = "+1-234-567-8910")
    private String phoneNumber;

    @ApiModelProperty(position = 5, example = "1990-12-31")
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
