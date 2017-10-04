package com.endava.user.management.web.controller.impl;

import com.endava.user.management.context.AppContext;
import com.endava.user.management.repository.UserRepository;
import com.endava.user.management.web.controller.AbstractController;
import com.endava.user.management.web.controller.ModelAndView;
import com.endava.user.management.web.controller.Request;

public class ListUsersController extends AbstractController {

	@Override
	public ModelAndView handleRequest(Request request) {
		UserRepository userRepository = (UserRepository) request.getHttpRequest().getAttribute(AppContext.Repository);
		ModelAndView model = new ModelAndView("index");
		model.addVariable("greeting", "Welcome to servlets!");
		model.addVariable("users", userRepository.findAll());
		return model;
	}
}
