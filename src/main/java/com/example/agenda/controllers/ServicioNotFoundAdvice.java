package com.example.agenda.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class ServicioNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ServicioNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String servicioNotFoundHandler(ServicioNotFoundException ex) {
        return ex.getMessage();
    }
}
