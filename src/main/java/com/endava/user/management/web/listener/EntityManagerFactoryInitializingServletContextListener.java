package com.endava.user.management.web.listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.endava.user.management.context.AppContext;

@WebListener
public class EntityManagerFactoryInitializingServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		addEntityManagerFactoryToContext(context);
	}

	private void addEntityManagerFactoryToContext(ServletContext context) {
		EntityManagerFactory emf = createEntityManagerFactory();
		setUpEntityManagerFactoryAttribute(context, emf);
	}

	private void setUpEntityManagerFactoryAttribute(ServletContext context, EntityManagerFactory emf) {
		synchronized (context) {
			context.setAttribute(AppContext.EntityManagerFactory, emf);
		}
	}

	private EntityManagerFactory createEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("user-management-tool");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		synchronized (context) {
			EntityManagerFactory emf = (EntityManagerFactory) context.getAttribute(AppContext.EntityManagerFactory);
			emf.close();
		}
	}
}
