package com.endava.user.management.web.controller.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.endava.user.management.context.AppContext;
import com.endava.user.management.domain.Gender;
import com.endava.user.management.domain.User;
import com.endava.user.management.service.UserService;
import com.endava.user.management.web.controller.AbstractController;
import com.endava.user.management.web.controller.ModelAndView;
import com.endava.user.management.web.controller.Request;
import com.endava.user.management.web.form.CreateUserForm;
import com.endava.user.management.web.form.validator.FormDataValidator;

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

	private ModelAndView showAddUserForm(Request request) {
		ModelAndView modelAndView = new ModelAndView("addUser");
		modelAndView.addVariable("genders", Gender.values());
		return modelAndView;
	}

	private ModelAndView createNewUser(Request request) {
		CreateUserForm userForm = request.getRequestParametersAs(CreateUserForm.class);
		if (userFormIsValid(userForm))
			return createUserAndRedirectToUserPage(request, userForm);
		return new ModelAndView("redirect:/addUser");
	}

	private ModelAndView createUserAndRedirectToUserPage(Request request, CreateUserForm userForm) {
		UserService service = (UserService) request.getHttpRequest().getAttribute(AppContext.Service);
		User user = service.create(userForm, request.getHttpRequest().getServletContext());
		return new ModelAndView("redirect:/users/" + user.getId());
	}

	private boolean userFormIsValid(CreateUserForm userForm) {
		FormDataValidator<CreateUserForm> validator = new FormDataValidator<>(userForm);
		validator.validate();
		return validator.isValid();
	}
}
