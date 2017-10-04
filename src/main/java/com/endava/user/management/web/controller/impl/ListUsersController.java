package com.endava.user.management.web.controller.impl;

import com.endava.user.management.context.AppContext;
import com.endava.user.management.service.UserService;
import com.endava.user.management.web.controller.AbstractController;
import com.endava.user.management.web.controller.ModelAndView;
import com.endava.user.management.web.controller.Request;

public class ListUsersController extends AbstractController {

	@Override
	public ModelAndView handleRequest(Request request) {
		UserService service = (UserService) request.getHttpRequest().getAttribute(AppContext.Service);
		ModelAndView model = new ModelAndView("index");
		model.addVariable("greeting", "Welcome to servlets!");
		model.addVariable("users", service.findAll());
		return model;
	}
}
