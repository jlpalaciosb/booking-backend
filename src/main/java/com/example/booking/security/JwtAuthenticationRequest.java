package com.example.booking.security;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;

public class JwtAuthenticationRequest {

    @ApiModelProperty(position = 0, example = "admin")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @ApiModelProperty(position = 1, example = "admin")
    @NotBlank(message = "Password is mandatory")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
