package com.example.booking.services.errors;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entityClass, Long id) {
        super("Cannot find " + entityClass + " with id = " + id);
    }
}
