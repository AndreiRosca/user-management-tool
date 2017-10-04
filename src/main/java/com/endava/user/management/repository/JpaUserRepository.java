package com.endava.user.management.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.endava.user.management.domain.User;

public class JpaUserRepository implements UserRepository {

	private final EntityManager em;

	public JpaUserRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<User> findAll() {
		return em.createQuery("select u from User u", User.class).getResultList();
	}

	@Override
	public User findById(long userId) {
		return em.find(User.class, userId);
	}

	@Override
	public User create(User user) {
		em.persist(user);
		return user;
	}

	@Override
	public User update(User user) {
		return em.merge(user);
	}

	@Override
	public void delete(long userId) {
		em.remove(findById(userId));
	}
}
