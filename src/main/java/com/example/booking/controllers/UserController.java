package com.example.booking.controllers;

import com.example.booking.models.User;
import com.example.booking.services.UserService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(tags = "Users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @ApiOperation(value = "List existing users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")})
    List<User> listUsers() {
        return userService.listUsers();
    }

    @PostMapping("/")
    @ApiOperation(value = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")})
    @ApiImplicitParams({ @ApiImplicitParam(
            name = "newUser", value = "New user", dataType = "UserPost")})
    User createService(@RequestBody @Valid User newUser) {
        return userService.createUser(newUser);
    }
}
