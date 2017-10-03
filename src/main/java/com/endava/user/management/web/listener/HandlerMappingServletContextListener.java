package com.endava.user.management.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.endava.user.management.context.AppContext;
import com.endava.user.management.web.mapping.HandlerMappingRegistry;
import com.endava.user.management.web.mapping.XmlHandlerMappingReader;

@WebListener
public class HandlerMappingServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		HandlerMappingRegistry registry = buildHandlerMappingRegistry();
		addHandlerMappingRegistryToContext(context, registry);
	}

	private void addHandlerMappingRegistryToContext(ServletContext context, HandlerMappingRegistry registry) {
		synchronized (context) {
			context.setAttribute(AppContext.MappingRegistry, registry);
		}
	}

	private HandlerMappingRegistry buildHandlerMappingRegistry() {
		return new XmlHandlerMappingReader("/controller-mapping.xml").parseMappingRegistry();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
