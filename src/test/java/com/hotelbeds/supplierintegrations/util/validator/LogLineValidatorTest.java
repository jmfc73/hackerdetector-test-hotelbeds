package com.hotelbeds.supplierintegrations.util.validator;

import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LINE_1;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LINE_1_1;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LINE_2;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LogLineValidatorTest {
    
    @InjectMocks
    private LogLineValidator logLineValidator;
    
    @Test
    void given_correct_format_line_with_signin_success_when_validate_then_return_true() {
        
        String logLine = LINE_1_1;
        boolean isValid = logLineValidator.validate(logLine);
        
        assertEquals(true, isValid);
    }
    
    @Test
    void given_correct_format_line_when_validate_then_return_true() {
        
        String logLine = LINE_1;
        boolean isValid = logLineValidator.validate(logLine);
        
        assertEquals(true, isValid);
    }
    
    @Test
    void given_incorrect_format_line_when_validate_then_return_false() {
        
        String logLine = LINE_2;
        boolean isValid = logLineValidator.validate(logLine);
        
        assertEquals(false, isValid);
    }
    
    

}
