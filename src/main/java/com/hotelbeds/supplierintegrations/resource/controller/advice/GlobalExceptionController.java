package com.hotelbeds.supplierintegrations.resource.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotelbeds.supplierintegrations.util.exception.HackerDetectorResponseException;

@RestControllerAdvice 
public class GlobalExceptionController {

    @ExceptionHandler(HackerDetectorResponseException.class)
    public ResponseEntity<Object> handleException(final HackerDetectorResponseException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getDescription());
    }

}
