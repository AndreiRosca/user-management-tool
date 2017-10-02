package com.endava.user.management.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.endava.user.management.web.util.TemplateEngineUtil;

@WebListener
public class ThymeleafConfigServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		TemplateEngine engine = buildTemplateEngine(sce.getServletContext());
		TemplateEngineUtil.setTemplateEngine(engine, sce.getServletContext());
	}

	private TemplateEngine buildTemplateEngine(ServletContext servletContext) {
		TemplateEngine engine = new TemplateEngine();
		engine.setTemplateResolver(buildTemplateResolver(servletContext));
		return engine;
	}

	private ITemplateResolver buildTemplateResolver(ServletContext servletContext) {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(servletContext);
		resolver.setPrefix("/WEB-INF/templates/");
		resolver.setTemplateMode(TemplateMode.HTML);
		return resolver;
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
