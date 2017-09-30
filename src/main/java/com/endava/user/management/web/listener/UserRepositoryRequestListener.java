package com.endava.user.management.web.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import com.endava.user.management.repository.InMemoryUserRepository;

@WebListener
public class UserRepositoryRequestListener implements ServletRequestListener {

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		ServletRequest request = sre.getServletRequest();
		request.setAttribute("repository", new InMemoryUserRepository());
	}

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
	}
}
