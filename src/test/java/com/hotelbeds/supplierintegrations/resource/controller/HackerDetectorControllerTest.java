package com.hotelbeds.supplierintegrations.resource.controller;

import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LogLineConstant.IP_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hotelbeds.supplierintegrations.business.HackerDetectorBusiness;
import com.hotelbeds.supplierintegrations.mockfactory.dto.LogLineDTOMockFactory;
import com.hotelbeds.supplierintegrations.resource.dto.LogLineDTO;
import com.hotelbeds.supplierintegrations.resource.dto.ResponseResultDTO;
import com.hotelbeds.supplierintegrations.util.ResponseEntityComponent;
import com.hotelbeds.supplierintegrations.util.exception.HackerDetectorResponseException;
import com.hotelbeds.supplierintegrations.util.validator.LogLineValidator;

@ExtendWith(MockitoExtension.class)
class HackerDetectorControllerTest {

    @InjectMocks
    private HackerDetectorController hackerDetectorController;

    @Mock
    private LogLineValidator logLineValidator;

    @Mock
    private HackerDetectorBusiness hackerDetectorBusiness;
    
    @Spy
    private ResponseEntityComponent responseEntity;

    @Test
    void given_line_with_signin_success_return_null() throws HackerDetectorResponseException {

        LogLineDTO logLineDTO = LogLineDTOMockFactory.createMockLogLineDTOCorrectLineAndSignInSuccess();
        
        when(logLineValidator.validate(Mockito.anyString())).thenReturn(true);
        when(hackerDetectorBusiness.parseLine(Mockito.anyString())).thenReturn(null);

        ResponseEntity<ResponseResultDTO<String>> response = hackerDetectorController.processLineHackerDetector(logLineDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNull(response.getBody().getResult());

        verify(logLineValidator, times(1)).validate(Mockito.anyString());
        verify(hackerDetectorBusiness, times(1)).parseLine(Mockito.anyString());

    }

    @Test
    void given_line_with_signin_failure_return_null() throws HackerDetectorResponseException {
        
        LogLineDTO logLineDTO = LogLineDTOMockFactory.createMockLogLineDTOCorrectLineAndSignInFailure();

        when(logLineValidator.validate(Mockito.anyString())).thenReturn(true);
        when(hackerDetectorBusiness.parseLine(Mockito.anyString())).thenReturn(null);

        ResponseEntity<ResponseResultDTO<String>> response = hackerDetectorController.processLineHackerDetector(logLineDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertNull(response.getBody().getResult());

        verify(logLineValidator, times(1)).validate(Mockito.anyString());
        verify(hackerDetectorBusiness, times(1)).parseLine(Mockito.anyString());
    }

    @Test
    void given_line_with_signin_failure_return_ip() throws HackerDetectorResponseException {

        LogLineDTO logLineDTO = LogLineDTOMockFactory.createMockLogLineDTOCorrectLineAndSignInFailure();
        
        when(logLineValidator.validate(Mockito.anyString())).thenReturn(true);
        when(hackerDetectorBusiness.parseLine(Mockito.anyString())).thenReturn(IP_1);

        ResponseEntity<ResponseResultDTO<String>> response = hackerDetectorController.processLineHackerDetector(logLineDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(IP_1, response.getBody().getResult());

        verify(logLineValidator, times(1)).validate(Mockito.anyString());
        verify(hackerDetectorBusiness, times(1)).parseLine(Mockito.anyString());
    }

    @Test
    void given_wrong_line_return_exception() throws HackerDetectorResponseException {
        
        LogLineDTO logLineDTO = LogLineDTOMockFactory.createMockLogLineDTOWrongLine();
        
        when(logLineValidator.validate(Mockito.anyString())).thenReturn(false);
        
        assertThrows(HackerDetectorResponseException.class,
                () -> hackerDetectorController.processLineHackerDetector(logLineDTO));
        
        verify(logLineValidator, times(1)).validate(Mockito.anyString());
    }
}
