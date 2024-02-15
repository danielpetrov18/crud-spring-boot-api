package com.example.housing.exception;

public class EstateNotFoundByIdException extends RuntimeException {

    public EstateNotFoundByIdException() {
    }

    public EstateNotFoundByIdException(String message) {
        super(message);
    }
}