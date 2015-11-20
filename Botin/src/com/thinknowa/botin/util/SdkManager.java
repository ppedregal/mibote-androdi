package com.thinknowa.botin.util;

import com.thinknowa.botin.sdk.Sdk;
import com.thinknowa.botin.sdk.interceptors.LoggingInterceptor;

public class SdkManager {
	
	private static final Sdk sdk = new Sdk("http://172.26.0.222:3000/api/");
	
	static {
	    sdk.logLevel(LoggingInterceptor.Level.BASIC);
	}
	
	public static Sdk getSdk(){
		return sdk;
	}

}
