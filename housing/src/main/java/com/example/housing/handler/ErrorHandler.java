package com.example.housing.handler;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;

import com.example.housing.exception.ControllerException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({ControllerException.class})
    public ResponseEntity<?> handleException(final ControllerException exception) {
        log.error("Error: {}, error msg: {}", exception.getErrorName(), exception.getMessage());
        return ResponseEntity.notFound().build();
    }

}