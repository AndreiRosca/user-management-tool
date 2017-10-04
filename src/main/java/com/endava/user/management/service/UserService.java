package com.endava.user.management.service;

import java.util.List;

import javax.servlet.ServletContext;

import com.endava.user.management.domain.User;
import com.endava.user.management.web.form.CreateUserForm;
import com.endava.user.management.web.form.UpdateUserForm;

public interface UserService {
	List<User> findAll();
	User findById(long userId);
	User create(User user);
	User create(CreateUserForm userForm, ServletContext context);
	User update(User user);
	User update(UpdateUserForm userForm);
	void delete(long userId);
}
