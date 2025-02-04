package com.example.ocare.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ExceptionTypes exceptionTypes;

    public CustomException(ExceptionTypes exceptionTypes) {
        super(exceptionTypes.getErrorResponse().getMessage());

        this.exceptionTypes = exceptionTypes;
    }

}
