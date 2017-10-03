package com.endava.user.management.web.controller;

public abstract class AbstractController implements Controller {

	private String urlMapping;

	@Override
	public void setUrlMapping(String urlMapping) {
		this.urlMapping = urlMapping;
	}
	
	@Override
	public String getUrlMapping() {
		return urlMapping;
	}
}
