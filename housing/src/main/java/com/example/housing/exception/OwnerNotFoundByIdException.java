package com.example.housing.exception;

public class OwnerNotFoundByIdException extends ControllerException {

    public OwnerNotFoundByIdException(String errorName) {
        super(errorName);
    }

    public OwnerNotFoundByIdException(String message, String errorName) {
        super(message, errorName);
    }

}