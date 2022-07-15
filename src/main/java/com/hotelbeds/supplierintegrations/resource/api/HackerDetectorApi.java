package com.hotelbeds.supplierintegrations.resource.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotelbeds.supplierintegrations.util.exception.HackerDetectorResponseException;

import io.swagger.annotations.Api;

@Api
public interface HackerDetectorApi {

    @PostMapping("/process-line")
    String processLineHackerDetector(@RequestBody final String processLine) throws HackerDetectorResponseException;
}
