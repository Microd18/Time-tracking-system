package com.example.timetrackingsystem.exceptions.handler;

import com.example.timetrackingsystem.exceptions.AirlineNotFoundException;
import com.example.timetrackingsystem.exceptions.ExecutionTimeTrackingException;
import com.example.timetrackingsystem.exceptions.PlaneNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler(AirlineNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String airlineNotFoundExceptionHandler(AirlineNotFoundException e) {
        return "Авиакомпания не найдена!";
    }

    @ExceptionHandler(PlaneNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String planeNotFoundExceptionHandler(PlaneNotFoundException e) {
        return "Самолет не найден!";
    }

    @ExceptionHandler(ExecutionTimeTrackingException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String executionTimeTrackingException(ExecutionTimeTrackingException e) {
        return "Ошибка подсчета времени!";
    }
}
