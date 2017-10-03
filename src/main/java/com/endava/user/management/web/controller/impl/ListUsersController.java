package com.endava.user.management.web.controller.impl;

import java.util.HashMap;
import java.util.Map;

import com.endava.user.management.context.AppContext;
import com.endava.user.management.repository.UserRepository;
import com.endava.user.management.web.controller.AbstractController;
import com.endava.user.management.web.controller.ModelAndView;
import com.endava.user.management.web.controller.Request;

public class ListUsersController extends AbstractController {

	@Override
	public ModelAndView handleRequest(Request request) {
		Map<String, Object> model = new HashMap<>();
		model.put("greeting", "Welcome to servlets!");
		UserRepository userRepository = (UserRepository) request.getHttpRequest().getAttribute(AppContext.Repository);
		model.put("users", userRepository.findAll());
		ModelAndView modelAndView = new ModelAndView("index", model);
		return modelAndView;
	}
}
