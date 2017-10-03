package com.endava.user.management.web.controller.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.endava.user.management.context.AppContext;
import com.endava.user.management.domain.Gender;
import com.endava.user.management.domain.User;
import com.endava.user.management.repository.UserRepository;
import com.endava.user.management.web.controller.AbstractController;
import com.endava.user.management.web.controller.ModelAndView;
import com.endava.user.management.web.controller.Request;

public class AddUserController extends AbstractController {
	private final Map<String, Function<Request, ModelAndView>> handlers = new HashMap<>();

	public AddUserController() {
		handlers.put("get", this::showAddUserForm);
		handlers.put("post", this::createNewUser);
	}

	@Override
	public ModelAndView handleRequest(Request request) {
		return handlers.get(request.getHttpMethod()).apply(request);
	}

	public ModelAndView showAddUserForm(Request request) {
		ModelAndView modelAndView = new ModelAndView("addUser");
		modelAndView.addVariable("genders", Gender.values());
		return modelAndView;
	}

	public ModelAndView createNewUser(Request request) {
		UserRepository repository = (UserRepository) request.getHttpRequest().getAttribute(AppContext.Repository);
		User user = repository.create(buildUserFromRequest(request));
		ModelAndView modelAndView = new ModelAndView("redirect:/users/" + user.getId());
		return modelAndView;
	}

	private User buildUserFromRequest(Request request) {
		User u = User.newBuilder().build();
		u.setId(100L);
		return u;
	}
}
