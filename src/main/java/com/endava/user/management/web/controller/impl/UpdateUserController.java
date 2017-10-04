package com.endava.user.management.web.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.endava.user.management.context.AppContext;
import com.endava.user.management.domain.Address;
import com.endava.user.management.domain.Framework;
import com.endava.user.management.domain.Gender;
import com.endava.user.management.domain.User;
import com.endava.user.management.repository.UserRepository;
import com.endava.user.management.web.controller.AbstractController;
import com.endava.user.management.web.controller.ModelAndView;
import com.endava.user.management.web.controller.Request;
import com.endava.user.management.web.form.CreateUserForm;

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
		CreateUserForm userForm = request.getRequestParametersAs(CreateUserForm.class);
		UserRepository repository = (UserRepository) request.getHttpRequest().getAttribute(AppContext.Repository);
		User user = repository.update(buildUserFromRequest(userForm));
		ModelAndView modelAndView = new ModelAndView("redirect:/users/" + user.getId());
		return modelAndView;
	}

	private User buildUserFromRequest(CreateUserForm userForm) {
		User user = User.newBuilder()
				.setId(Long.valueOf(userForm.getId()))
				.setName(userForm.getName())
				.setEmail(userForm.getEmail())
				.setSex(Gender.valueOf(userForm.getGender().toUpperCase()))
				.setFrameworks(getFormFrameworks(userForm))
				.setAddress(Address.newBuilder()
						.setCountry(userForm.getCountry())
						.setCity(userForm.getCity())
						.setState(userForm.getState())
						.setZipCode(userForm.getZipCode())
						.build())
				.build();
		return user;
	}

	private List<Framework> getFormFrameworks(CreateUserForm userForm) {
		return userForm.getFrameworks()
				.stream()
				.map(Framework::new)
				.collect(Collectors.toList());
	}

	private ModelAndView deleteUser(Request request) {
		long userId = Long.valueOf(request.getPathParameter("userId"));
		UserRepository repository = (UserRepository) request.getHttpRequest().getAttribute(AppContext.Repository);
		repository.delete(userId);
		return new ModelAndView();
	}
}
