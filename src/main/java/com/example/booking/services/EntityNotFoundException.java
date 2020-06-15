package com.example.booking.services;

class EntityNotFoundException extends RuntimeException {

    EntityNotFoundException(String entityClass, Long id) {
        super("Cannot find " + entityClass + " with id = " + id);
    }
}
