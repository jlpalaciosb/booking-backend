package com.example.booking.services;

import com.example.booking.models.User;
import java.util.List;

public interface UserService {
    List<User> listUsers();
    User createUser(User newUser);
}
