package com.endava.user.management.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.endava.user.management.web.util.TemplateEngineUtil;

@WebServlet(urlPatterns = { "/" })
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(getServletContext());
		WebContext context = new WebContext(request, response, getServletContext());
		context.setVariable("greeting", "Welcome to servlets!");
		engine.process("index.html", context, response.getWriter());
	}
}
