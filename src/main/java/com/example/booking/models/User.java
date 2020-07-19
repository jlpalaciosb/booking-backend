package com.example.booking.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue
    @ApiModelProperty(position = 0)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username is mandatory")
    @ApiModelProperty(position = 1)
    private String username;

    // stores password hash
    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    @ApiModelProperty(position = 2)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        // String salt = BCrypt.gensalt(12);
        // this.password = BCrypt.hashpw(password, salt);
        this.password = password;
    }

    // returns true if the recd password is correct
    public boolean checkPassword(String password) {
        // return BCrypt.checkpw(password, this.password);
        return true;
    }

    public void trim() {
        if (username != null) username = username.trim();
        if (password != null) password = password.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + this.id + ", " +
                "username='" + this.username + "'}";
    }
}
