package com.example.timetrackingsystem.exceptions.handler;

import com.example.timetrackingsystem.exceptions.AirlineNotFoundException;
import com.example.timetrackingsystem.exceptions.PlaneNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String airlineNotFoundExceptionHandler(AirlineNotFoundException e) {
        return "Авиакомпания не найдена!";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String planeNotFoundExceptionHandler(PlaneNotFoundException e) {
        return "Самолет не найден!";
    }
}
