package com.endava.user.management.renderer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.endava.user.management.web.controller.ModelAndView;
import com.endava.user.management.web.util.TemplateEngineUtil;

public class ViewRenderer {
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private final ModelAndView modelAndView;

	public ViewRenderer(HttpServletRequest request, HttpServletResponse reponse, ModelAndView modelAndView) {
		this.request = request;
		this.response = reponse;
		this.modelAndView = modelAndView;
	}

	public void render() {
		try {
			tryRender();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void tryRender() throws IOException {
		if (modelAndView.hasView())
			renderView();
	}

	private void renderView() throws IOException {
		if (viewNameIsRedirect())
			redirect();
		else
			processTemplateAndSendToClient();
	}

	private void processTemplateAndSendToClient() throws IOException {
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		WebContext context = new WebContext(request, response, request.getServletContext());
		modelAndView.getModel().forEach((key, value) -> context.setVariable(key, value));
		engine.process(modelAndView.getViewName(), context, response.getWriter());
	}

	private void redirect() {
		try {
			tryRedirect();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void tryRedirect() throws IOException {
		String viewName = modelAndView.getViewName();
		String redirectUrl = skipRedirectTokenFromViewName(viewName);
		response.sendRedirect(redirectUrl);
	}

	private String skipRedirectTokenFromViewName(String viewName) {
		return viewName.substring(viewName.indexOf(":") + 1);
	}

	private boolean viewNameIsRedirect() {
		return modelAndView.getViewName().startsWith("redirect:");
	}
}
