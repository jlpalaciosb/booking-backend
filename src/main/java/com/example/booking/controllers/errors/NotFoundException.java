package com.example.booking.controllers.errors;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entityClass, Long id) {
        super("Unable to find " + entityClass + " with id " + id);
    }
}
