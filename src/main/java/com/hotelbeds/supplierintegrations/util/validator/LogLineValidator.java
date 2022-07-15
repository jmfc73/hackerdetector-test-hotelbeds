package com.hotelbeds.supplierintegrations.util.validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.stereotype.Component;

import com.hotelbeds.supplierintegrations.util.SigninState;

@Component
public class LogLineValidator {
    
    public boolean validate(String line) {

        String[] strings = line.split(",");
        InetAddressValidator validator = InetAddressValidator.getInstance();

        boolean isCorrectSize = strings.length == 4;
        boolean isCorrectIp = validator.isValidInet4Address(strings[0]);
        boolean isCorrectLong = StringUtils.isNumeric(strings[1]);
        boolean isCorrectState = strings[2].equals(SigninState.SIGNIN_FAILURE.name())
                || strings[2].equals(SigninState.SIGNIN_SUCCESS.name());

        return (isCorrectSize && isCorrectIp && isCorrectLong && isCorrectState);
    }
}
