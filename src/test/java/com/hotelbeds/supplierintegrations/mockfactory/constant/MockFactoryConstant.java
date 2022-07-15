package com.hotelbeds.supplierintegrations.mockfactory.constant;

import com.hotelbeds.supplierintegrations.util.SigninState;

public class MockFactoryConstant {

    public static final short MAX_ATTEMPTS_SIGN_IN = 5;
    public static final short MINUTES_TO_REVIEW = 5;
    
    public static final String LINE_1 = "192.152.1.12,655655655,SIGNIN_FAILURE,Pepito.Pepin";
    public static final String LINE_1_1 = "192.152.1.12,655655655,SIGNIN_SUCCESS,Pepito.Pepin";
    public static final String LINE_2 = "192.152.1.12,655655655,SIGNIN_FAILURE,Pepito.Pepin,Fulanito";
    public static final String RFC2822_1 = "Thu, 21 Dec 2000 16:01:07 +0200";
    public static final String RFC2822_2 = "Thu, 21 Dec 2000 16:06:07 +0200";

	public static final class LogLineConstant {

		public static final String IP_1 = "192.152.1.12";		
		public static final long LOCAL_DATE_TIME_1 = 655655655;
		public static final long LOCAL_DATE_TIME_2 = 655655656;
		public static final long LOCAL_DATE_TIME_3 = 655655657;
		public static final long LOCAL_DATE_TIME_4 = 655655658;
		public static final long LOCAL_DATE_TIME_5 = 655655659;
		public static final SigninState STATUS_1 = SigninState.SIGNIN_FAILURE;
		public static final SigninState STATUS_2 = SigninState.SIGNIN_SUCCESS;
		
		

		private LogLineConstant() {
		}

	}

}