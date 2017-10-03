package com.endava.user.management.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.endava.user.management.context.AppContext;
import com.endava.user.management.web.mapping.ControllerMapping;
import com.endava.user.management.web.mapping.HandlerMappingRegistry;
import com.endava.user.management.web.mapping.RequestMapping;

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
		HandlerMappingRegistry registry = new HandlerMappingRegistry();
		
		ControllerMapping mapping = new ControllerMapping("com.endava.user.management.web.controller.impl.ListUsersController");
		mapping.addRequestMapping(new RequestMapping("get", "/welcome"));
		registry.addControllerMapping(mapping);
		
		mapping = new ControllerMapping("com.endava.user.management.web.controller.impl.UpdateUserController");
		mapping.addRequestMapping(new RequestMapping("get", "/users/{userId}/update"));
		mapping.addRequestMapping(new RequestMapping("post", "/users/{userId}/update"));
		mapping.addRequestMapping(new RequestMapping("delete", "/users/{userId}/delete"));
		registry.addControllerMapping(mapping);
		
		mapping = new ControllerMapping("com.endava.user.management.web.controller.impl.UserDetailsController");
		mapping.addRequestMapping(new RequestMapping("get", "/users/{userId}"));
		registry.addControllerMapping(mapping);
		
		mapping = new ControllerMapping("com.endava.user.management.web.controller.impl.AddUserController");
		mapping.addRequestMapping(new RequestMapping("get", "/addUser"));
		registry.addControllerMapping(mapping);
		
		return registry;
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
