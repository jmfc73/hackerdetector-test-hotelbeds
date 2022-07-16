package com.hotelbeds.supplierintegrations.util.parser;

import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LINE_1;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LogLineConstant.LOCAL_DATE_TIME_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.hotelbeds.supplierintegrations.util.LocalDateTimeUtils;

@SpringBootTest
class LogLineParserTest {
    
    @InjectMocks
    private LogLineParser logLineParser;
    
    @Mock
    private LocalDateTimeUtils localDateTimeUtils;
    
    @Autowired
    private LocalDateTimeUtils localDateTimeUtilsAux;
    
    @Test
    void given_correct_format_line_when_parse_then_return_result() {

        LocalDateTime localDateTime = localDateTimeUtilsAux.toLocalDateTime(LOCAL_DATE_TIME_1);
        LogLine expectedResult = LogLineMockFactories.createMockLogLineSigninFailure();
        expectedResult.setLocalDateTime(localDateTime);
        
        when(localDateTimeUtils.toLocalDateTime(Mockito.anyLong())).thenReturn(localDateTime);
        
        LogLine result = logLineParser.parse(LINE_1);
        
        assertNotNull(result);
        assertEquals(expectedResult.getIp(), result.getIp());
        assertEquals(expectedResult.getLocalDateTime(), result.getLocalDateTime());
        assertEquals(expectedResult.getStatus(), result.getStatus());
        verify(localDateTimeUtils, times(1)).toLocalDateTime(Mockito.anyLong());
        
    }
}
