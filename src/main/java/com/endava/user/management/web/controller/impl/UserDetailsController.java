package com.endava.user.management.web.controller.impl;

import java.util.HashMap;
import java.util.Map;

import com.endava.user.management.context.AppContext;
import com.endava.user.management.repository.UserRepository;
import com.endava.user.management.web.controller.AbstractController;
import com.endava.user.management.web.controller.ModelAndView;
import com.endava.user.management.web.controller.Request;

public class UserDetailsController extends AbstractController {

	@Override
	public ModelAndView handleRequest(Request request) {
		Map<String, Object> model = new HashMap<>();
		UserRepository userRepository = (UserRepository) request.getHttpRequest().getAttribute(AppContext.Repository);
		model.put("user", userRepository.findById(Long.valueOf(request.getPathParameter("userId"))));
		ModelAndView modelAndView = new ModelAndView("queryUser", model);
		return modelAndView;
	}
}
