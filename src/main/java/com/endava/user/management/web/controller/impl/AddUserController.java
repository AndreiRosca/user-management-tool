package com.endava.user.management.web.controller.impl;

import com.endava.user.management.domain.Gender;
import com.endava.user.management.web.controller.AbstractController;
import com.endava.user.management.web.controller.ModelAndView;
import com.endava.user.management.web.controller.Request;

public class AddUserController extends AbstractController {

	@Override
	public ModelAndView handleRequest(Request request) {
		ModelAndView modelAndView = new ModelAndView("addUser");
		modelAndView.addVariable("genders", Gender.values());
		return modelAndView;
	}
}
