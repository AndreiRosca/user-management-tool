package com.endava.user.management.web.util;

import javax.servlet.ServletContext;

import org.thymeleaf.TemplateEngine;

public class TemplateEngineUtil {
	private static final String TEMPLATE_ENGINE = "com.endava.user.management.web.util.TemplateEngine";

	public static void setTemplateEngine(TemplateEngine engine, ServletContext servletContext) {
		synchronized (servletContext) {
			servletContext.setAttribute(TEMPLATE_ENGINE, engine);
		}
	}

	public static TemplateEngine getTemplateEngine(ServletContext servletContext) {
		synchronized (servletContext) {
			return (TemplateEngine) servletContext.getAttribute(TEMPLATE_ENGINE);
		}
	}
}
