package com.hotelbeds.supplierintegrations.resource.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotelbeds.supplierintegrations.resource.dto.ResponseDTO;
import com.hotelbeds.supplierintegrations.util.ResponseEntityComponent;
import com.hotelbeds.supplierintegrations.util.exception.HackerDetectorResponseException;

@RestControllerAdvice 
public class GlobalExceptionController {
    
    @Autowired
    private ResponseEntityComponent responseEntityComponent;

    @ExceptionHandler(HackerDetectorResponseException.class)
    public ResponseEntity<ResponseDTO> handleException(final HackerDetectorResponseException ex) {

        return responseEntityComponent.getResponseEntityWithError(ex);
    }

}
