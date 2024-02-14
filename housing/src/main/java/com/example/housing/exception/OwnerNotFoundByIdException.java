package com.example.housing.exception;

public class OwnerNotFoundByIdException extends RuntimeException {

    public OwnerNotFoundByIdException() {
    }

    public OwnerNotFoundByIdException(String message) {
        super(message);
    }
}