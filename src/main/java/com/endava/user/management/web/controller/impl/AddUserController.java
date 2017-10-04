package com.endava.user.management.web.controller.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.endava.user.management.context.AppContext;
import com.endava.user.management.domain.Address;
import com.endava.user.management.domain.Gender;
import com.endava.user.management.domain.User;
import com.endava.user.management.repository.UserRepository;
import com.endava.user.management.web.controller.AbstractController;
import com.endava.user.management.web.controller.ModelAndView;
import com.endava.user.management.web.controller.Request;
import com.endava.user.management.web.form.CreateUserForm;
import com.endava.user.management.web.util.FileUtil;

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
		CreateUserForm userForm = request.getRequestParametersAs(CreateUserForm.class);
		FileUtil util = new FileUtil(request.getHttpRequest().getServletContext());
		String cvFilePath = util.persistCvFile(userForm.getCvFile());
		User user = repository.create(buildUserFromRequest(userForm, cvFilePath));
		ModelAndView modelAndView = new ModelAndView("redirect:/users/" + user.getId());
		return modelAndView;
	}

	private User buildUserFromRequest(CreateUserForm userForm, String cvFilePath) {
		User user = User.newBuilder()
				.setName(userForm.getName())
				.setEmail(userForm.getEmail())
				.setSex(Gender.valueOf(userForm.getGender().toUpperCase()))
				.setCvFilePath(cvFilePath)
				.setAddress(Address.newBuilder()
						.setCountry(userForm.getCountry())
						.setCity(userForm.getCity())
						.setState(userForm.getState())
						.setZipCode(userForm.getZipCode())
						.build())
				.build();
		return user;
	}
}
