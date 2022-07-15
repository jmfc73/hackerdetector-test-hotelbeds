package com.hotelbeds.supplierintegrations.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeUtils {
    
    public LocalDateTime toLocalDateTime(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        return zonedDateTime.toLocalDateTime();
    }
    
    public long getMinutesBetweenTwoDateTimes(String dateTime1, String dateTime2) {
        
        ZonedDateTime zonedDateTime1 = parseTimestamp(dateTime1);
        ZonedDateTime zonedDateTime2 = parseTimestamp(dateTime2);
        
        return ChronoUnit.MINUTES.between(zonedDateTime1, zonedDateTime2);        
    }
    
    private ZonedDateTime parseTimestamp(String dateTime) {
        return ZonedDateTime.parse(dateTime, DateTimeFormatter.RFC_1123_DATE_TIME);
    }
    
}
