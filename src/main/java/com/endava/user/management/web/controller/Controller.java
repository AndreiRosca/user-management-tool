package com.endava.user.management.web.controller;

public interface Controller {
	ModelAndView handleRequest(Request request);
	void setUrlMapping(String urlMapping);
	String getUrlMapping();
}
