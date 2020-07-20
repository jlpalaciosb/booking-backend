package com.example.booking.services;

import com.example.booking.errors.BadRequestException;
import com.example.booking.models.User;
import com.example.booking.repositories.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> listUsers() {
        return userRepo.findAll();
    }

    @Override
    public User createUser(User newUser) {
        newUser.trim();
        newUser.setRole("USER");
        validate(null, newUser);
        return userRepo.save(newUser);
    }

    private void validate(User oldUser, User newUser) {
        boolean creating = (oldUser == null);
        if ((creating || !oldUser.getUsername().equals(oldUser.getUsername())) &&
                userRepo.existsByUsername(newUser.getUsername())) {
            throw new BadRequestException("There is another user with username = " + newUser.getUsername());
        }
    }
}
