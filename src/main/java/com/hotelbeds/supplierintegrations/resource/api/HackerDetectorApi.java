package com.hotelbeds.supplierintegrations.resource.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotelbeds.supplierintegrations.resource.dto.LogLineDTO;
import com.hotelbeds.supplierintegrations.resource.dto.ResponseResultDTO;
import com.hotelbeds.supplierintegrations.util.exception.HackerDetectorResponseException;

import io.swagger.annotations.Api;

@Api
public interface HackerDetectorApi {

    @PostMapping("/process-line")
    ResponseEntity<ResponseResultDTO<String>> processLineHackerDetector(@Valid @RequestBody LogLineDTO logLineDTO) throws HackerDetectorResponseException;
}
