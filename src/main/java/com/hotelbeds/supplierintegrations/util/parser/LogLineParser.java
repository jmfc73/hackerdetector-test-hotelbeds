package com.hotelbeds.supplierintegrations.util.parser;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hotelbeds.supplierintegrations.domain.LogLine;
import com.hotelbeds.supplierintegrations.util.LocalDateTimeUtils;
import com.hotelbeds.supplierintegrations.util.SigninState;

@Component
public class LogLineParser{
    
    @Autowired
    LocalDateTimeUtils localDateTimeUtils;

    public LogLine parse(String line) {

        String[] strings = line.split(",");
        long longTime = Long.parseLong(strings[1]);
        LocalDateTime localDateTime = localDateTimeUtils.toLocalDateTime(longTime);
        return LogLine.builder()
                .ip(strings[0])
                .localDateTime(localDateTime)
                .status(SigninState.getSigninState(strings[2]))
                .build();

    }
    
    


    
}