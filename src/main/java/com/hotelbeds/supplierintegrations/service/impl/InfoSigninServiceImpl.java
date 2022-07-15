package com.hotelbeds.supplierintegrations.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.hotelbeds.supplierintegrations.domain.InfoIpSignin;
import com.hotelbeds.supplierintegrations.domain.LogLine;
import com.hotelbeds.supplierintegrations.service.InfoSigninService;
import com.hotelbeds.supplierintegrations.util.SigninState;

import lombok.Getter;

@Component
public class InfoSigninServiceImpl implements InfoSigninService {

    @Value("${signIn.maxAttempts}")
    private short maxAttemptsSignIn;
    @Value("${signIn.minutesToReview}")
    private short minutesToReview;
    
    @Getter
    private static Map<String, InfoIpSignin> infoIpsSigninMap = Collections.synchronizedMap(new HashMap<>());


    @Override
    public boolean isMaxAttemptsSigninExceeded(LogLine logLine) {

        if (SigninState.SIGNIN_SUCCESS.equals(logLine.getStatus())) {
            removeInfoIpSigninSuccess(logLine.getIp());
            return false;
        }
        
        return failedAttemptsSignInExcedeedMax(logLine);
    }
    
    @Override
    public void removeInfoIpSigninWithExceededMinutesToReview(LocalDateTime localDateTime) {
        for (Map.Entry<String, InfoIpSignin> entry : infoIpsSigninMap.entrySet()) {
            if ((entry.getValue()).firstAttemptExceededMinutesToReview(localDateTime, minutesToReview)) {
                infoIpsSigninMap.remove(entry.getKey());
            }
        }
    }

    private boolean failedAttemptsSignInExcedeedMax(LogLine logLine) {
        
        updatedFailedAttemptsSignIn(logLine);
        
        return getFailedAttemptsSignInIpValueByLogLine(logLine).getAttemptsSignin() >= maxAttemptsSignIn;
    }
    
    private synchronized void updatedFailedAttemptsSignIn(LogLine logLine) {
        
        InfoIpSignin infoIpSignin = getFailedAttemptsSignInIpValueByLogLine(logLine);        
        infoIpSignin.updateFailedAttemptsSignInOnMinutesToReview(logLine.getLocalDateTime(), minutesToReview);
    }

    private InfoIpSignin getFailedAttemptsSignInIpValueByLogLine(LogLine logLine) {

        if (infoIpsSigninMap.get(logLine.getIp()) == null) {
            infoIpsSigninMap.put(logLine.getIp(), new InfoIpSignin(logLine.getLocalDateTime()));
        }
        return infoIpsSigninMap.get(logLine.getIp());
    }
    
    private void removeInfoIpSigninSuccess(String ip) {
        infoIpsSigninMap.remove(ip);
    }
    
}
