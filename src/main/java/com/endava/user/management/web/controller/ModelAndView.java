package com.endava.user.management.web.controller;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
	private String viewName = "";
	private Map<String, Object> model = new HashMap<>();

	public ModelAndView(String viewName, Map<String, Object> model) {
		this.viewName = viewName;
		this.model = model;
	}
	
	public ModelAndView(String viewName) {
		this.viewName = viewName;
	}
	
	public ModelAndView() {
	}

	public String getViewName() {
		return viewName;
	}

	public Map<String, Object> getModel() {
		return model;
	}
	
	public void addVariable(String variableName, Object value) {
		model.put(variableName, value);
	}
	
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public boolean hasView() {
		return !viewName.isEmpty();
	}
}
