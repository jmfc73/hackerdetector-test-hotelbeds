package com.hotelbeds.supplierintegrations.domain;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class InfoIpSignin {

    private short attemptsSignin = 1;

    private LocalDateTime localDateTimeFirstAttemptSignIn;

    public InfoIpSignin(LocalDateTime localDateTimeFirstAttemptSignIn) {        
        this.localDateTimeFirstAttemptSignIn = localDateTimeFirstAttemptSignIn;
    }

    public synchronized void updateFailedAttemptsSignInOnMinutesToReview(LocalDateTime localDateTimeLastAttemptSignIn,
            short minutesToReview) {

        if (localDateTimeFirstAttemptSignIn.isBefore(localDateTimeLastAttemptSignIn) && localDateTimeFirstAttemptSignIn
                .plusMinutes(minutesToReview).isAfter(localDateTimeLastAttemptSignIn)) {
            attemptsSignin++;
        } else {
            attemptsSignin = 1;
            localDateTimeFirstAttemptSignIn = localDateTimeLastAttemptSignIn;
        }
    }
    
    public synchronized boolean firstAttemptExceededMinutesToReview(LocalDateTime localDateTime, short minutesToReview) {
        
        return localDateTime.minusMinutes(minutesToReview).isAfter(localDateTimeFirstAttemptSignIn);
    }
}