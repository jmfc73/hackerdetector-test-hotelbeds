package com.hotelbeds.supplierintegrations.util;

import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.RFC2822_1;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.RFC2822_2;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LocalDateTimeUtilsTest {
    
    @InjectMocks
    private LocalDateTimeUtils localDateTimeUtils;
    
    @Test
    void given_date_times_when_get_minutes_then_return_minutes() {
        
        long result = localDateTimeUtils.getMinutesBetweenTwoDateTimes(RFC2822_1, RFC2822_2);

        assertEquals(5, result);        
        
    }

    
    

}
