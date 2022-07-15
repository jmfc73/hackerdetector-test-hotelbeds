package com.hotelbeds.supplierintegrations.business.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelbeds.supplierintegrations.business.HackerDetectorBusiness;
import com.hotelbeds.supplierintegrations.domain.LogLine;
import com.hotelbeds.supplierintegrations.service.InfoSigninService;
import com.hotelbeds.supplierintegrations.util.parser.LogLineParser;

@Service
public class HackerDetectorBusinessImpl implements HackerDetectorBusiness {

    @Autowired
    private LogLineParser logLineParser;
    
    @Autowired
    private InfoSigninService infoSigninService;
    
    @Override
    public String parseLine(final String line) {

        LogLine logLine = logLineParser.parse(line);
        
        infoSigninService.removeInfoIpSigninWithExceededMinutesToReview(logLine.getLocalDateTime());

        return (infoSigninService.isMaxAttemptsSigninExceeded(logLine) ? logLine.getIp() : null);
    }
}
