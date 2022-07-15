package com.hotelbeds.supplierintegrations.util.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class HackerDetectorResponseException extends Exception {

    private static final long serialVersionUID = 4795743334818359268L;

    private final String description;

    private final HttpStatus httpStatus;

    public HackerDetectorResponseException(String description, HttpStatus httpStatus) {

        this.description = description;
        this.httpStatus = httpStatus;
    }

}
