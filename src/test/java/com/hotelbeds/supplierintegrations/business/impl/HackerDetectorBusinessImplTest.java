package com.hotelbeds.supplierintegrations.business.impl;

import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LINE_1;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LogLineConstant.IP_1;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LogLineConstant.LOCAL_DATE_TIME_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotelbeds.supplierintegrations.domain.LogLine;
import com.hotelbeds.supplierintegrations.mockfactory.domain.LogLineMockFactories;
import com.hotelbeds.supplierintegrations.service.InfoSigninService;
import com.hotelbeds.supplierintegrations.util.LocalDateTimeUtils;
import com.hotelbeds.supplierintegrations.util.parser.LogLineParser;

@SpringBootTest
class HackerDetectorBusinessImplTest {

	@InjectMocks
	private HackerDetectorBusinessImpl hackerDetectorBusiness;

	@Mock
	private LogLineParser logLineParser;
	
	@Mock
	private InfoSigninService infoSigninService;
	
	@Autowired
    private LocalDateTimeUtils localDateTimeUtils;
	
	@Test
    void given_correct_format_line_and_one_attempts_when_parse_then_return_null() {
	    
        LocalDateTime localDateTime = localDateTimeUtils.toLocalDateTime(LOCAL_DATE_TIME_1);
        LogLine logLine = LogLineMockFactories.createMockLogLineSigninFailure();
        logLine.setLocalDateTime(localDateTime);
	    
	    when(logLineParser.parse(Mockito.anyString())).thenReturn(logLine);
	    doNothing().when(infoSigninService).removeInfoIpSigninWithExceededMinutesToReview(Mockito.any(LocalDateTime.class));
	    when(infoSigninService.isMaxAttemptsSigninExceeded(Mockito.any(LogLine.class))).thenReturn(false);
	    
	    String result = hackerDetectorBusiness.parseLine(LINE_1);
	    
	    assertNull(result);
	    verify(logLineParser, times(1)).parse(Mockito.anyString());
	    verify(infoSigninService, times(1)).removeInfoIpSigninWithExceededMinutesToReview(Mockito.any(LocalDateTime.class));
	    verify(infoSigninService, times(1)).isMaxAttemptsSigninExceeded(Mockito.any(LogLine.class));
	    
	}
	
	@Test
    void given_correct_format_line_and_four_attempts_when_parse_then_return_ip() {
        
        LocalDateTime localDateTime = localDateTimeUtils.toLocalDateTime(LOCAL_DATE_TIME_1);
        LogLine logLine = LogLineMockFactories.createMockLogLineSigninFailure();
        logLine.setLocalDateTime(localDateTime);
        
        when(logLineParser.parse(Mockito.anyString())).thenReturn(logLine);
        doNothing().when(infoSigninService).removeInfoIpSigninWithExceededMinutesToReview(Mockito.any(LocalDateTime.class));
        when(infoSigninService.isMaxAttemptsSigninExceeded(Mockito.any(LogLine.class))).thenReturn(true);
        
        String result = hackerDetectorBusiness.parseLine(LINE_1);
        
        assertEquals(IP_1, result);
        verify(logLineParser, times(1)).parse(Mockito.anyString());
        verify(infoSigninService, times(1)).removeInfoIpSigninWithExceededMinutesToReview(Mockito.any(LocalDateTime.class));
        verify(infoSigninService, times(1)).isMaxAttemptsSigninExceeded(Mockito.any(LogLine.class));
        
    }
}
