package com.endava.user.management.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.endava.user.management.renderer.ViewRenderer;
import com.endava.user.management.web.controller.Controller;
import com.endava.user.management.web.controller.ModelAndView;
import com.endava.user.management.web.controller.Request;
import com.endava.user.management.web.util.HandlerMappingUtil;

@WebServlet(urlPatterns = { "/*" })
@MultipartConfig
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String httpMethod = request.getMethod().toLowerCase();
		String pathInfo = request.getPathInfo();
		Controller controller = new HandlerMappingUtil(getServletContext()).getControllerFor(httpMethod, pathInfo);
		ModelAndView modelAndView = controller.handleRequest(Request.newBuilder()
				.setHttpMethod(httpMethod)
				.setHttpRequest(request)
				.setHttpResponse(response)
				.setUrlInfo(pathInfo)
				.setUrlMapping(controller.getUrlMapping())
				.build());
		ViewRenderer renderer = new ViewRenderer(request, response, modelAndView);
		renderer.render();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
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
