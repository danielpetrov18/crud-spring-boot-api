package com.example.housing.exception;

public class EstateNotFoundByIdException extends ControllerException {
    public EstateNotFoundByIdException(String errorName) {
        super(errorName);
    }

    public EstateNotFoundByIdException(String message, String errorName) {
        super(message, errorName);
    }

}