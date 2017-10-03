package com.endava.user.management.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import com.endava.user.management.domain.Address;
import com.endava.user.management.domain.Framework;
import com.endava.user.management.domain.Gender;
import com.endava.user.management.domain.User;

public class InMemoryUserRepository implements UserRepository {
	private static final AtomicLong idGenerator = new AtomicLong();
	private static final Map<Long, User> users = new ConcurrentHashMap<>();
	private static boolean isInitialized;

	public InMemoryUserRepository() {
		if (!isInitialized) { 
			User u1 = User.newBuilder()
					.setSex(Gender.MALE)
					.setName("John Smith")
					.setEmail("john@gmail.com")
					.setBirthDate(new Date())
					.setAddress(Address.newBuilder()
							.setCountry("USA")
							.setCity("Los Angeles")
							.setState("California")
							.setZipCode("1234")
							.build())
					.addFramework(new Framework("Hibernate"))
					.addFramework(new Framework("Spring MVC"))
					.addFramework(new Framework("JSF"))
					.build();
			create(u1 );
			
			User u2 = User.newBuilder()
					.setSex(Gender.FEMALE)
					.setName("Vanessa Kate")
					.setEmail("vanessa@gmail.com")
					.setBirthDate(new Date())
					.setAddress(Address.newBuilder()
							.setCountry("USA")
							.setCity("New York City")
							.setState("New York")
							.setZipCode("5454")
							.build())
					.addFramework(new Framework("JDBC"))
					.addFramework(new Framework("Spring Boot"))
					.addFramework(new Framework("ActiveMQ"))
					.build();
			create(u2);
			isInitialized = true;			
		}
	}

	@Override
	public List<User> findAll() {
		return users.values()
				.stream()
				.collect(Collectors.toList());
	}

	@Override
	public User findById(long userId) {
		return users.values()
				.stream()
				.filter(u -> u.getId().equals(userId))
				.findFirst()
				.get();
	}

	@Override
	public User create(User user) {
		user.setId(idGenerator.incrementAndGet());
		users.put(user.getId(), user);
		return user;
	}

	@Override
	public User update(User user) {
		users.put(user.getId(), user);
		return user;
	}

	@Override
	public void delete(long userId) {
		users.remove(userId);
	}
}
