package com.example.housing.controller;

import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;

import com.example.housing.exception.ControllerException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ControllerException.class})
    public ResponseEntity<?> handleException(final ControllerException exception) {
        log.error("TIME: {}, Error name: {}, error msg: {}", LocalDateTime.now(),
                exception.getErrorName(), exception.getMessage());
        return ResponseEntity.notFound().build();
    }

}
