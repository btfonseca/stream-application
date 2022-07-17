package com.streamaplication.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	public void flushPrintWriter(HttpServletResponse resp, String message) throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
	    out.print(message);
	    out.flush(); 
	}
}
