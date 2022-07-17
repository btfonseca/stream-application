package com.streamaplication.util;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import com.streamaplication.cache.GenericCache;

public class Util {
	
	private GenericCache<String, Long> cache = new GenericCache<>();
	
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
	
	public Boolean clientExist(String clientId ) {
		return this.cache.get(clientId).isPresent();
	}
}
