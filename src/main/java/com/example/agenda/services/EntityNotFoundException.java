package com.example.agenda.services;

class EntityNotFoundException extends RuntimeException {

    EntityNotFoundException(String entityClass, Long id) {
        super("No se pudo encontrar " + entityClass + " con id=" + id);
    }
}
