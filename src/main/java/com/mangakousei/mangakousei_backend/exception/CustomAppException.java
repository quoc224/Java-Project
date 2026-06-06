package com.mangakousei.mangakousei_backend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomAppException extends RuntimeException {
    private final HttpStatus httpStatus;

    public CustomAppException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}