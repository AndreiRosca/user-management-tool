package com.endava.user.management.repository;

import java.util.List;

import com.endava.user.management.domain.User;

public interface UserRepository {

	List<User> findAll();
	User findById(long userId);
	User create(User user);
	User update(User user);
	void delete(long userId);
}
