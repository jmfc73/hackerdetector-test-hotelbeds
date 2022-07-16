package com.hotelbeds.supplierintegrations.mockfactory.dto;

import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LINE_1;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LINE_1_1;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LINE_2;

import com.hotelbeds.supplierintegrations.resource.dto.LogLineDTO;

public class LogLineDTOMockFactory {

	public static LogLineDTO createMockLogLineDTOCorrectLineAndSignInSuccess() {

	    LogLineDTO logLineDTO = LogLineDTO.builder().processLine(LINE_1_1).build();
	    
	    return logLineDTO;
	}
	
	public static LogLineDTO createMockLogLineDTOCorrectLineAndSignInFailure() {

        LogLineDTO logLineDTO = LogLineDTO.builder().processLine(LINE_1).build();
        
        return logLineDTO;
    }
	
	public static LogLineDTO createMockLogLineDTOWrongLine() {

        LogLineDTO logLineDTO = LogLineDTO.builder().processLine(LINE_2).build();
        
        return logLineDTO;
    }

}
