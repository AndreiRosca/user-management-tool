package com.endava.user.management.web.listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import com.endava.user.management.context.AppContext;
import com.endava.user.management.repository.JpaUserRepository;
import com.endava.user.management.service.UserServiceImpl;

@WebListener
public class UserRepositoryRequestListener implements ServletRequestListener {
	private final Map<ServletRequest, EntityManager> entityManagers = new ConcurrentHashMap<>();

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		ServletRequest request = sre.getServletRequest();
		EntityManager em = createEntityManagerAndStartTransaction(request.getServletContext());
		entityManagers.put(request, em);
		setAttributes(request, em);
	}

	private void setAttributes(ServletRequest request, EntityManager em) {
		JpaUserRepository repository = new JpaUserRepository(em);
		request.setAttribute(AppContext.Repository, new JpaUserRepository(em));
		request.setAttribute(AppContext.Service, new UserServiceImpl(repository));
	}

	private EntityManager createEntityManagerAndStartTransaction(ServletContext servletContext) {
		EntityManagerFactory emf = (EntityManagerFactory) servletContext.getAttribute(AppContext.EntityManagerFactory);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		return em;
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		ServletRequest request = sre.getServletRequest();
		EntityManager em = entityManagers.get(request);
		entityManagers.remove(request);
		EntityTransaction transaction = em.getTransaction();
		if (transaction.isActive())
			transaction.commit();
		em.close();
	}
}
