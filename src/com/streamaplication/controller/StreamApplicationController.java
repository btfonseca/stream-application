package com.streamaplication.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.streamaplication.model.InputTopic;
import com.streamaplication.model.OutputTopic;
import com.streamaplication.model.ResponseIpStack;
import com.streamaplication.util.Util;

public class StreamApplicationController extends Util {
	
	private final String ACCESSKEY = "c7efb9561b3fa3aeb00de7d5011b84d5";
	private final String URLIPSTACK = "http://api.ipstack.com/";
	
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
	
	public String sendRequestToIpStack(InputTopic it, HttpServletRequest req) throws IOException {
		URL whatismylocation;
		if(it.getIp() != null) {
			whatismylocation = new URL(URLIPSTACK+it.getIp()+"?access_key="+ACCESSKEY);
		} else {
			whatismylocation = new URL(URLIPSTACK+getForwarderIpRequest(req)+"?access_key="+ACCESSKEY);
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(whatismylocation.openStream()));
		String json = in.readLine();
		
		return json;
	}
	
	public ResponseIpStack convertJsonToResponseIpStack(String json) throws JsonSyntaxException, IOException {
		ResponseIpStack ris = g.fromJson(json, ResponseIpStack.class);
		return ris;
	}
	
	public String setOutputTopic(InputTopic it, ResponseIpStack ris) {
		OutputTopic ot = new OutputTopic();
		ot.setClientId(it.getClientId());
		ot.setTimestamp(it.getTimestamp());
		ot.setIp(it.getIp());
		ot.setLatitude(ris.getLatitude());
		ot.setLongitude(ris.getLongitude());
		ot.setCountry(ris.getCountryName());
		ot.setRegion(ris.getRegionName());
		ot.setCity(ris.getCity());
		
		String output = g.toJson(ot);
		
		return output;
	}
}
