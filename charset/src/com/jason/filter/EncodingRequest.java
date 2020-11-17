package com.jason.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	public EncodingRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	public String getParameter(String name) {
		String value = request.getParameter(name);
		try {
			value = new String(value.getBytes(),"utf-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return value;
	}

}
