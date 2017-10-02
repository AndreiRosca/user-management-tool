package com.endava.user.management.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.endava.user.management.repository.UserRepository;
import com.endava.user.management.web.util.TemplateEngineUtil;

@WebServlet(urlPatterns = { "/*" })
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
	
	private void oldHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(getServletContext());
		WebContext context = new WebContext(request, response, getServletContext());
		context.setVariable("greeting", "Welcome to servlets!");
		UserRepository userRepository = (UserRepository) request.getAttribute("repository");
		context.setVariable("users", userRepository.findAll());
		engine.process("index", context, response.getWriter());
	}

	private void handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequest(request, response);
	}

	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequest(request, response);
	}

	@Override
	protected void doTrace(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequest(request, response);
	}
}
