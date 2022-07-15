package com.hotelbeds.supplierintegrations.service;

import java.time.LocalDateTime;

import com.hotelbeds.supplierintegrations.domain.LogLine;

public interface InfoSigninService {
    
    void removeInfoIpSigninWithExceededMinutesToReview(LocalDateTime localDateTime);
    
    boolean isMaxAttemptsSigninExceeded(LogLine logLine);
}
