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
import com.endava.user.management.web.form.UpdateUserForm;
import com.endava.user.management.web.form.validator.FormDataValidator;

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

	private UserService getUserService(Request request) {
		return (UserService) request.getHttpRequest().getAttribute(AppContext.Service);
	}

	private ModelAndView showUpdateUserForm(Request request) {
		UserService service = getUserService(request);
		long userId = Long.valueOf(request.getPathParameter("userId"));
		return new ModelAndView("updateUser")
				.addVariable("user", service.findById(userId))
				.addVariable("genders", Gender.values());
	}

	private ModelAndView handleUpdateUserFormSubmision(Request request) {
		UpdateUserForm userForm = request.getRequestParametersAs(UpdateUserForm.class);
		if (userFormIsValid(userForm))
			return updateUserAndRedirectToUserPage(request, userForm);
		return new ModelAndView("redirect:/users/" + request.getPathParameter("userId") + "/update");
	}

	private ModelAndView updateUserAndRedirectToUserPage(Request request, UpdateUserForm userForm) {
		UserService service = getUserService(request);
		User user = service.update(userForm);
		return new ModelAndView("redirect:/users/" + user.getId());
	}

	private boolean userFormIsValid(UpdateUserForm userForm) {
		FormDataValidator<UpdateUserForm> validator = new FormDataValidator<>(userForm);
		validator.validate();
		return validator.isValid();
	}

	private ModelAndView deleteUser(Request request) {
		UserService service = getUserService(request);
		long userId = Long.valueOf(request.getPathParameter("userId"));
		service.delete(userId);
		return new ModelAndView();
	}
}
