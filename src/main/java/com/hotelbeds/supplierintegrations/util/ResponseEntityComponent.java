package com.hotelbeds.supplierintegrations.util;

import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotelbeds.supplierintegrations.resource.dto.ErrorDTO;
import com.hotelbeds.supplierintegrations.resource.dto.ResponseDTO;
import com.hotelbeds.supplierintegrations.resource.dto.ResponseResultDTO;
import com.hotelbeds.supplierintegrations.util.exception.HackerDetectorResponseException;

@Component
public class ResponseEntityComponent {

    @RequestMapping(produces = MediaType.APPLICATION_JSON)
    public <T> ResponseEntity<ResponseResultDTO<T>> getResponseEntity(final HttpStatus status, final T result) {
        return ResponseEntity.status(status).body(getResponse(result));
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<ResponseDTO> getResponseEntityWithError(final HackerDetectorResponseException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(getErrorResponse(ex.getDescription()));
    }

    private ResponseDTO getErrorResponse(final String description) {
        return new ResponseDTO(new ErrorDTO(description));
    }

    private <T> ResponseResultDTO<T> getResponse(final T result) {
        return ResponseResultDTO.<T>builder().result(result).build();
    }

}
