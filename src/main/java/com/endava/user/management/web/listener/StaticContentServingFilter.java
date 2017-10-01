package com.endava.user.management.web.listener;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = { "/static/*", "*.ico" })
public class StaticContentServingFilter implements Filter {

	private RequestDispatcher defaultDispatcher;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		defaultDispatcher.forward(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		defaultDispatcher = filterConfig.getServletContext().getNamedDispatcher("default");
	}
}
