package com.hotelbeds.supplierintegrations.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hotelbeds.supplierintegrations.business.HackerDetectorBusiness;
import com.hotelbeds.supplierintegrations.resource.api.HackerDetectorApi;
import com.hotelbeds.supplierintegrations.resource.dto.LogLineDTO;
import com.hotelbeds.supplierintegrations.resource.dto.ResponseResultDTO;
import com.hotelbeds.supplierintegrations.util.ResponseEntityComponent;
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
    
    @Autowired
    private ResponseEntityComponent responseEntity;
    
    @Override
    public ResponseEntity<ResponseResultDTO<String>> processLineHackerDetector(LogLineDTO logLineDTO)
            throws HackerDetectorResponseException {

        if (!logLineValidator.validate(logLineDTO.getProcessLine())) {
            log.error("hacker-detector-error validating line: {} ", logLineDTO.getProcessLine());
            throw new HackerDetectorResponseException("Invalid line", HttpStatus.BAD_REQUEST);
        }
        
        final String ip = hackerDetectorBusiness.parseLine(logLineDTO.getProcessLine());

        return responseEntity.getResponseEntity(HttpStatus.OK, ip);

    }

}
