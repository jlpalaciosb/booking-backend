package com.example.booking.controllers.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ClientErrorAdvice {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> notFoundHandler(NotFoundException ex) {
        Map<String, Object> r = new LinkedHashMap<>();
        r.put("status", HttpStatus.NOT_FOUND.value());
        r.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        r.put("message", ex.getMessage());
        return r;
    }

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> badRequestHandler(BadRequestException ex) {
        Map<String, Object> r = new LinkedHashMap<>();
        r.put("status", HttpStatus.BAD_REQUEST.value());
        r.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        r.put("message", ex.getMessage());
        return r;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> r = new LinkedHashMap<>();
        r.put("status", HttpStatus.BAD_REQUEST.value());
        r.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        r.put("message", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return r;
    }
}
