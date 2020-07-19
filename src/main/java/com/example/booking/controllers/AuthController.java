package com.example.booking.controllers;

import com.example.booking.services.AuthService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Api(tags = "Auth")
public class AuthController {

    private final AuthService authService;

    AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/token")
    @ApiOperation(value = "Get authorization token")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")})
    public String getToken(@RequestParam String username, @RequestParam String password) {
        return "xyz";
    }
}
