package com.endava.user.management.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

	private Long id;
	private String name;
	private String email;
	private List<Framework> frameworks = new ArrayList<>();
	private Address address;
	private Sex sex;
	private Date birthDate;

	private User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public List<Framework> getFrameworks() {
		return frameworks;
	}

	public Address getAddress() {
		return address;
	}

	public Sex getSex() {
		return sex;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public static UserBuilder newBuilder() {
		return new UserBuilder();
	}

	public static class UserBuilder {
		private User user = new User();

		public UserBuilder setName(String name) {
			user.name = name;
			return this;
		}

		public UserBuilder setEmail(String email) {
			user.email = email;
			return this;
		}

		public UserBuilder setAddress(Address address) {
			user.address = address;
			return this;
		}

		public UserBuilder setSex(Sex sex) {
			user.sex = sex;
			return this;
		}

		public UserBuilder setBirthDate(Date birthDate) {
			user.birthDate = birthDate;
			return this;
		}
		
		public UserBuilder addFramework(Framework framework) {
			user.frameworks.add(framework);
			return this;
		}

		public User build() {
			return user;
		}
	}
}
