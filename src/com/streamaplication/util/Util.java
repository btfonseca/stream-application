package com.streamaplication.util;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

public class Util {
	
	public long getEpochMilli() {
		return Instant.now().toEpochMilli();
	}
	
	public String getForwarderIpRequest(HttpServletRequest req) {
		String ipAddress = req.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {  
		    ipAddress = req.getRemoteAddr();  
		}
		return ipAddress;
	}
}
