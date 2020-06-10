package com.example.agenda.controllers;

class ServicioNotFoundException extends RuntimeException {

    ServicioNotFoundException(Long id) {
        super("No se pudo encontrar el servicio con id=" + id);
    }
}
