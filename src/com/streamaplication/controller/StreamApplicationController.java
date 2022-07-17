package com.streamaplication.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.streamaplication.model.InputTopic;
import com.streamaplication.util.Util;

public class StreamApplicationController extends Util {
	
	private Gson g = new Gson();

	public InputTopic convertRequestToInputTopic(HttpServletRequest req) throws JsonSyntaxException, IOException {
		InputTopic it = new InputTopic();
		
		String clientId = req.getParameter("ClientId");
		if (clientId != null) {
			it.setClientId(clientId);
		}
		
		String timestamp = req.getParameter("TimeStamp");
		if (timestamp != null) {
			it.setTimestamp(timestamp);
		} else {
			it.setTimestamp(""+getEpochMilli());
		}
		
		String ip = req.getParameter("Ip");
		if (ip != null) {
			it.setIp(ip);
		} else {
			it.setIp(getForwarderIpRequest(req));
		}
		
		return it;
	}
}
