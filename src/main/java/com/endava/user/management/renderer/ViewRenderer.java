package com.endava.user.management.renderer;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.endava.user.management.web.util.TemplateEngineUtil;

public class ViewRenderer {
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private final Map<String, Object> model;
	private final String viewName;

	public ViewRenderer(HttpServletRequest request, HttpServletResponse reponse, Map<String, Object> model,
			String viewName) {
		this.request = request;
		this.response = reponse;
		this.model = model;
		this.viewName = viewName;
	}

	public void render() {
		try {
			tryRender();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void tryRender() throws IOException {
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		WebContext context = new WebContext(request, response, request.getServletContext());
		model.forEach((key, value) -> context.setVariable(key, value));
		engine.process(viewName, context, response.getWriter());
	}
}
