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
	private Gender sex;
	private Date birthDate;
	private String cvFilePath;

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

	public Gender getSex() {
		return sex;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getCvFilePath() {
		return cvFilePath;
	}

	public void setCvFilePath(String cvFilePath) {
		this.cvFilePath = cvFilePath;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", frameworks=" + frameworks + ", address="
				+ address + ", sex=" + sex + ", birthDate=" + birthDate + ", cvFilePath=" + cvFilePath + "]";
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

		public UserBuilder setSex(Gender sex) {
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

		public UserBuilder setCvFilePath(String cvFilePath) {
			user.cvFilePath = cvFilePath;
			return this;
		}
		
		public UserBuilder setFrameworks(List<Framework> frameworks) {
			user.frameworks = frameworks;
			return this;
		}

		public User build() {
			return user;
		}
	}
}
