package com.hotelbeds.supplierintegrations.mockfactory.domain;

import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LogLineConstant.IP_1;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LogLineConstant.STATUS_1;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LogLineConstant.STATUS_2;

import com.hotelbeds.supplierintegrations.domain.LogLine;

public class LogLineMockFactories {

    public static LogLine createMockLogLineSigninFailure() {

        LogLine logLine = LogLine.builder().ip(IP_1)
                .status(STATUS_1).build();

        return logLine;

    }
    
    public static LogLine createMockLogLineSigninSuccess() {

        LogLine logLine = LogLine.builder().ip(IP_1)
                .status(STATUS_2).build();

        return logLine;

    }

}
