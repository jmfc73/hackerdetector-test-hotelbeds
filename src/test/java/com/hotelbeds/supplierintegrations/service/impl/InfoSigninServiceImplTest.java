package com.hotelbeds.supplierintegrations.service.impl;

import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.MAX_ATTEMPTS_SIGN_IN;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.MINUTES_TO_REVIEW;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LogLineConstant.LOCAL_DATE_TIME_1;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LogLineConstant.LOCAL_DATE_TIME_2;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LogLineConstant.LOCAL_DATE_TIME_3;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LogLineConstant.LOCAL_DATE_TIME_4;
import static com.hotelbeds.supplierintegrations.mockfactory.constant.MockFactoryConstant.LogLineConstant.LOCAL_DATE_TIME_5;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ReflectionUtils;

import com.hotelbeds.supplierintegrations.domain.LogLine;
import com.hotelbeds.supplierintegrations.mockfactory.domain.LogLineMockFactories;
import com.hotelbeds.supplierintegrations.util.LocalDateTimeUtils;

@SpringBootTest
class InfoSigninServiceImplTest {

    @InjectMocks
    private InfoSigninServiceImpl infoSigninService;

    @Autowired
    private LocalDateTimeUtils localDateTimeUtils;

    @Test
    void given_line_with_signin_success_and_not_exist_when_process_then_return_false() {

        LogLine logLineSigninSuccess = LogLineMockFactories.createMockLogLineSigninSuccess();

        boolean result = infoSigninService.isMaxAttemptsSigninExceeded(logLineSigninSuccess);

        assertEquals(false, result);

    }

    @Test
    void given_line_with_signin_success_and_exist_when_process_then_remove_and_return_false() {

        LocalDateTime localDateTime = localDateTimeUtils.toLocalDateTime(LOCAL_DATE_TIME_1);

        LogLine logLineSigninFailure = LogLineMockFactories.createMockLogLineSigninFailure();
        logLineSigninFailure.setLocalDateTime(localDateTime);
        LogLine logLineSigninSuccess = LogLineMockFactories.createMockLogLineSigninSuccess();

        infoSigninService.isMaxAttemptsSigninExceeded(logLineSigninFailure);

        boolean result = infoSigninService.isMaxAttemptsSigninExceeded(logLineSigninSuccess);

        assertEquals(false, result);

    }

    @Test
    void given_line_with_signin_failure_and_not_exist_when_process_then_add_to_list_and_return_false() {

        initProperties(MAX_ATTEMPTS_SIGN_IN, MINUTES_TO_REVIEW);

        LocalDateTime localDateTime = localDateTimeUtils.toLocalDateTime(LOCAL_DATE_TIME_1);

        LogLine logLineSigninFailure = LogLineMockFactories.createMockLogLineSigninFailure();
        logLineSigninFailure.setLocalDateTime(localDateTime);

        boolean result = infoSigninService.isMaxAttemptsSigninExceeded(logLineSigninFailure);

        assertEquals(false, result);

    }
    
    @Test
    void given_line_with_signin_failure_and_exist_when_process_then_add_to_list_and_return_false() {

        initProperties(MAX_ATTEMPTS_SIGN_IN, MINUTES_TO_REVIEW);

        LocalDateTime localDateTime1 = localDateTimeUtils.toLocalDateTime(LOCAL_DATE_TIME_1);
        LocalDateTime localDateTime2 = localDateTimeUtils.toLocalDateTime(LOCAL_DATE_TIME_2);

        LogLine logLineSigninFailure1 = LogLineMockFactories.createMockLogLineSigninFailure();
        logLineSigninFailure1.setLocalDateTime(localDateTime1);
        infoSigninService.isMaxAttemptsSigninExceeded(logLineSigninFailure1);
        
        LogLine logLineSigninFailure2 = LogLineMockFactories.createMockLogLineSigninFailure();
        logLineSigninFailure2.setLocalDateTime(localDateTime2);

        boolean result = infoSigninService.isMaxAttemptsSigninExceeded(logLineSigninFailure2);

        assertEquals(false, result);

    }
    
    @Test
    void given_line_with_signin_failure_and_exist_with_four_attempts_when_process_then_add_to_list_and_return_true() {

        initProperties(MAX_ATTEMPTS_SIGN_IN, MINUTES_TO_REVIEW);

        LocalDateTime localDateTime1 = localDateTimeUtils.toLocalDateTime(LOCAL_DATE_TIME_1);
        LocalDateTime localDateTime2 = localDateTimeUtils.toLocalDateTime(LOCAL_DATE_TIME_2);
        LocalDateTime localDateTime3 = localDateTimeUtils.toLocalDateTime(LOCAL_DATE_TIME_3);
        LocalDateTime localDateTime4 = localDateTimeUtils.toLocalDateTime(LOCAL_DATE_TIME_4);
        LocalDateTime localDateTime5 = localDateTimeUtils.toLocalDateTime(LOCAL_DATE_TIME_5);

        LogLine logLineSigninFailure1 = LogLineMockFactories.createMockLogLineSigninFailure();
        logLineSigninFailure1.setLocalDateTime(localDateTime1);
        infoSigninService.isMaxAttemptsSigninExceeded(logLineSigninFailure1);
        
        LogLine logLineSigninFailure2 = LogLineMockFactories.createMockLogLineSigninFailure();
        logLineSigninFailure2.setLocalDateTime(localDateTime2);
        infoSigninService.isMaxAttemptsSigninExceeded(logLineSigninFailure2);
        
        LogLine logLineSigninFailure3 = LogLineMockFactories.createMockLogLineSigninFailure();
        logLineSigninFailure3.setLocalDateTime(localDateTime3);
        infoSigninService.isMaxAttemptsSigninExceeded(logLineSigninFailure3);
        
        LogLine logLineSigninFailure4 = LogLineMockFactories.createMockLogLineSigninFailure();
        logLineSigninFailure4.setLocalDateTime(localDateTime4);
        infoSigninService.isMaxAttemptsSigninExceeded(logLineSigninFailure4);
        
        LogLine logLineSigninFailure5 = LogLineMockFactories.createMockLogLineSigninFailure();
        logLineSigninFailure5.setLocalDateTime(localDateTime5);

        boolean result = infoSigninService.isMaxAttemptsSigninExceeded(logLineSigninFailure5);

        assertEquals(true, result);

    }
    
    @Test
    void given_info_with_minutes_exceeded_when_process_then_removed_from_list_and_get_empty_map() {
        
        initProperties(MAX_ATTEMPTS_SIGN_IN, MINUTES_TO_REVIEW);
        
        LocalDateTime localDateTime1 = localDateTimeUtils.toLocalDateTime(LOCAL_DATE_TIME_1);
        LogLine logLineSigninFailure1 = LogLineMockFactories.createMockLogLineSigninFailure();
        logLineSigninFailure1.setLocalDateTime(localDateTime1);
        infoSigninService.isMaxAttemptsSigninExceeded(logLineSigninFailure1);
        
        LocalDateTime localDateTime2 = localDateTimeUtils.toLocalDateTime(LOCAL_DATE_TIME_2);
                
        infoSigninService.removeInfoIpSigninWithExceededMinutesToReview(localDateTime2.plusMinutes(MINUTES_TO_REVIEW));
        
        assertTrue(InfoSigninServiceImpl.getInfoIpsSigninMap().isEmpty());
        
    }
    
    @Test
    void given_info_with_minutes_not_exceeded_when_process_then_get_not_empty_map() {
        
        initProperties(MAX_ATTEMPTS_SIGN_IN, MINUTES_TO_REVIEW);
        
        LocalDateTime localDateTime1 = localDateTimeUtils.toLocalDateTime(LOCAL_DATE_TIME_1);
        LogLine logLineSigninFailure1 = LogLineMockFactories.createMockLogLineSigninFailure();
        logLineSigninFailure1.setLocalDateTime(localDateTime1);
        infoSigninService.isMaxAttemptsSigninExceeded(logLineSigninFailure1);
                
        infoSigninService.removeInfoIpSigninWithExceededMinutesToReview(localDateTime1);
        
        assertFalse(InfoSigninServiceImpl.getInfoIpsSigninMap().isEmpty());
        
    }

    private void initProperties(short maxAttemptsSignIn, short minutesToReview) {
        Field maxAttemptsSignInField = ReflectionUtils.findField(InfoSigninServiceImpl.class, "maxAttemptsSignIn");
        Field minutesToReviewField = ReflectionUtils.findField(InfoSigninServiceImpl.class, "minutesToReview");

        ReflectionUtils.makeAccessible(maxAttemptsSignInField);
        ReflectionUtils.makeAccessible(minutesToReviewField);

        ReflectionUtils.setField(maxAttemptsSignInField, infoSigninService, maxAttemptsSignIn);
        ReflectionUtils.setField(minutesToReviewField, infoSigninService, minutesToReview);
    }

}
