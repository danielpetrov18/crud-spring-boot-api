package com.example.housing.exception;

import lombok.Getter;

public class ControllerException extends RuntimeException {

    @Getter
    private final String errorName;

    public ControllerException(String errorName) {
        this.errorName = errorName;
    }

    public ControllerException(String message, String errorName) {
        super(message);
        this.errorName = errorName;
    }

}