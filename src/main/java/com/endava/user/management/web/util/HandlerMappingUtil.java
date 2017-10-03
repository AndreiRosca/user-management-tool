package com.endava.user.management.web.util;

import javax.servlet.ServletContext;

import com.endava.user.management.context.AppContext;
import com.endava.user.management.web.controller.Controller;
import com.endava.user.management.web.mapping.HandlerMappingRegistry;

public class HandlerMappingUtil {

	private final ServletContext servletContext;

	public HandlerMappingUtil(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public Controller getControllerFor(String httpMethod, String pathInfo) {
		HandlerMappingRegistry registry = getMappingRegistry();
		return registry.getController(httpMethod, pathInfo).get();
	}

	private HandlerMappingRegistry getMappingRegistry() {
		synchronized (servletContext) {
			return (HandlerMappingRegistry) servletContext.getAttribute(AppContext.MappingRegistry);			
		}
	}
}
