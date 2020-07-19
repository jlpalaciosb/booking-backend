package com.example.booking.models.apidoc;

import io.swagger.annotations.ApiModelProperty;

public class UserDoc {

    @ApiModelProperty(position = 0, example = "admin")
    private String username;

    @ApiModelProperty(position = 1, example = "admin")
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

    public static abstract class UserPost extends UserDoc {}
}
