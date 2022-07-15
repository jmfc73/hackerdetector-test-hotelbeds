package com.hotelbeds.supplierintegrations.resource.controller;

import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LINE_1;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LINE_1_1;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LINE_2;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LogLineConstant.IP_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotelbeds.supplierintegrations.business.HackerDetectorBusiness;
import com.hotelbeds.supplierintegrations.util.exception.HackerDetectorResponseException;
import com.hotelbeds.supplierintegrations.util.validator.LogLineValidator;

@SpringBootTest
class HackerDetectorControllerTest {

    @InjectMocks
    private HackerDetectorController hackerDetectorController;

    @Mock
    private LogLineValidator logLineValidator;

    @Mock
    private HackerDetectorBusiness hackerDetectorBusiness;

    @Test
    void given_line_with_signin_success_return_null() throws HackerDetectorResponseException {

        when(logLineValidator.validate(Mockito.anyString())).thenReturn(true);
        when(hackerDetectorBusiness.parseLine(Mockito.anyString())).thenReturn(null);

        String result = hackerDetectorController.processLineHackerDetector(LINE_1_1);

        assertNull(result);

        verify(logLineValidator, times(1)).validate(Mockito.anyString());
        verify(hackerDetectorBusiness, times(1)).parseLine(Mockito.anyString());

    }

    @Test
    void given_line_with_signin_failure_return_null() throws HackerDetectorResponseException {

        when(logLineValidator.validate(Mockito.anyString())).thenReturn(true);
        when(hackerDetectorBusiness.parseLine(Mockito.anyString())).thenReturn(null);

        String result = hackerDetectorController.processLineHackerDetector(LINE_1);

        assertNull(result);

        verify(logLineValidator, times(1)).validate(Mockito.anyString());
        verify(hackerDetectorBusiness, times(1)).parseLine(Mockito.anyString());
    }

    @Test
    void given_line_with_signin_failure_return_ip() throws HackerDetectorResponseException {

        when(logLineValidator.validate(Mockito.anyString())).thenReturn(true);
        when(hackerDetectorBusiness.parseLine(Mockito.anyString())).thenReturn(IP_1);

        String result = hackerDetectorController.processLineHackerDetector(LINE_1);

        assertEquals(IP_1, result);

        verify(logLineValidator, times(1)).validate(Mockito.anyString());
        verify(hackerDetectorBusiness, times(1)).parseLine(Mockito.anyString());
    }

    @Test
    void given_wrong_line_return_exception() throws HackerDetectorResponseException {
        
        when(logLineValidator.validate(Mockito.anyString())).thenReturn(false);
        
        assertThrows(HackerDetectorResponseException.class,
                () -> hackerDetectorController.processLineHackerDetector(LINE_2));
        
        verify(logLineValidator, times(1)).validate(Mockito.anyString());
    }
}
