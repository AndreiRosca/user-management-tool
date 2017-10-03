package com.endava.user.management.web.controller.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.endava.user.management.context.AppContext;
import com.endava.user.management.domain.Gender;
import com.endava.user.management.repository.UserRepository;
import com.endava.user.management.web.controller.AbstractController;
import com.endava.user.management.web.controller.ModelAndView;
import com.endava.user.management.web.controller.Request;

public class UpdateUserController extends AbstractController {
	private final Map<String, Function<Request, ModelAndView>> handlers = new HashMap<>();

	public UpdateUserController() {
		handlers.put("get", this::showUpdateUserForm);
		handlers.put("post", this::handleUpdateUserFormSubmision);
		handlers.put("delete", this::deleteUser);
	}

	@Override
	public ModelAndView handleRequest(Request request) {
		return handlers.get(request.getHttpMethod()).apply(request);
	}

	private ModelAndView showUpdateUserForm(Request request) {
		UserRepository repository = (UserRepository) request.getHttpRequest().getAttribute(AppContext.Repository);
		Map<String, Object> model = new HashMap<>();
		model.put("user", repository.findById(Long.valueOf(request.getPathParameter("userId"))));
		model.put("genders", Gender.values());
		return new ModelAndView("updateUser", model);
	}

	private ModelAndView handleUpdateUserFormSubmision(Request request) {
		return null;
	}

	private ModelAndView deleteUser(Request request) {
		long userId = Long.valueOf(request.getPathParameter("userId"));
		UserRepository repository = (UserRepository) request.getHttpRequest().getAttribute(AppContext.Repository);
		repository.delete(userId);
		return new ModelAndView();
	}
}
