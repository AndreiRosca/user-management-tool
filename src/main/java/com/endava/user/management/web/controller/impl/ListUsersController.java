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
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addVariable("greeting", "Welcome to servlets!");
		modelAndView.addVariable("users", userRepository.findAll());
		return modelAndView;
	}
}
