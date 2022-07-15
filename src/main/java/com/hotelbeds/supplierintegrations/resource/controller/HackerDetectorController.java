package com.hotelbeds.supplierintegrations.resource.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbeds.supplierintegrations.business.HackerDetectorBusiness;
import com.hotelbeds.supplierintegrations.resource.api.HackerDetectorApi;
import com.hotelbeds.supplierintegrations.util.exception.HackerDetectorResponseException;
import com.hotelbeds.supplierintegrations.util.validator.LogLineValidator;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class HackerDetectorController implements HackerDetectorApi {

    @Autowired
    private LogLineValidator logLineValidator;    
    
    @Autowired
    private HackerDetectorBusiness hackerDetectorBusiness;
    
    @Override
    public String processLineHackerDetector(@NotNull String processLine) throws HackerDetectorResponseException {
        
        if (!logLineValidator.validate(processLine)) {
            log.error("hacker-detector-error validating line: {} ", processLine);
            throw new HackerDetectorResponseException("Invalid line", HttpStatus.BAD_REQUEST); 
        }
        
        return hackerDetectorBusiness.parseLine(processLine);
    }

}
