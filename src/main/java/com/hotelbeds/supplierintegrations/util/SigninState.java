package com.hotelbeds.supplierintegrations.util;

public enum SigninState {
    SIGNIN_SUCCESS, SIGNIN_FAILURE;    
    
    public static SigninState getSigninState(String action) {
        if (SigninState.SIGNIN_SUCCESS.name().equals(action)) {
            return SigninState.SIGNIN_SUCCESS;
        } else {
            return SigninState.SIGNIN_FAILURE;
        }
    }
}
