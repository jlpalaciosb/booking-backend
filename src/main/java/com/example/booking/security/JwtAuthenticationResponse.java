package com.example.booking.security;

import io.swagger.annotations.ApiModelProperty;

public class JwtAuthenticationResponse {

    @ApiModelProperty(position = 0)
    private String token;

    @ApiModelProperty(position = 1)
    private String tokenType;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
        this.tokenType = "Bearer";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
