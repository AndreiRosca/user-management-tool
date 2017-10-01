package com.endava.user.management.web.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.endava.user.management.domain.Sex;
import com.endava.user.management.repository.UserRepository;
import com.endava.user.management.web.util.TemplateEngineUtil;

@WebServlet(urlPatterns = { "/users/*" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		String[] parts = pathInfo.split("/");
		long userId = Long.valueOf(parts[1]);
		UserRepository repository = (UserRepository) request.getAttribute("repository");
		repository.delete(userId);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Path path = new Path(request.getPathInfo());
		long userId = path.getUserId();
		if (path.requestIsUpdateUser()) {
			updateUser(request, response, userId);
		} else {
			queryUser(request, response, userId);
		}
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response, long userId) throws IOException {
		UserRepository repository = (UserRepository) request.getAttribute("repository");
		Map<String, Object> model = new HashMap<>();
		model.put("user", repository.findById(userId));
		model.put("genders", Sex.values());
		renderModel(request, response, "updateUser.html", model);
	}
	
	private void renderModel(HttpServletRequest request, HttpServletResponse response, 
			String viewName, Map<String, Object> model) throws IOException {
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(getServletContext());
		WebContext context = new WebContext(request, response, getServletContext());
		model.forEach((key, value) -> context.setVariable(key, value));
		engine.process(viewName, context, response.getWriter());		
	}

	private void queryUser(HttpServletRequest request, HttpServletResponse response, long userId) throws IOException {
		UserRepository repository = (UserRepository) request.getAttribute("repository");
		Map<String, Object> model = Collections.singletonMap("user", repository.findById(userId));
		renderModel(request, response, "queryUser.html", model);
	}

	private static class Path {
		private final String[] parts;

		public Path(String pathInfo) {
			parts = pathInfo.split("/");
		}

		public long getUserId() {
			return Long.valueOf(parts[1]);
		}

		public boolean requestIsQueryUser() {
			return !requestIsUpdateUser();
		}

		public boolean requestIsUpdateUser() {
			return parts.length > 2 && "update".equalsIgnoreCase(parts[2]);
		}
	}
}
