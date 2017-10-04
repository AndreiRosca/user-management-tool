package com.endava.user.management.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private String email;
	
	@ElementCollection
	@CollectionTable(name = "user_frameworks", joinColumns = @JoinColumn(name = "user_id"))
	private List<Framework> frameworks = new ArrayList<>();
	
	@Embedded
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private Gender sex;

	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Column(name = "cv_file_path")
	private String cvFilePath;

	@Lob
	@Column(name = "cv_file_content")
	private byte[] cvFileContent;

	protected User() {
	}

	public Long getId() {
		return id;
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

	public byte[] getCvFileContent() {
		return cvFileContent;
	}

	public void setCvFileContent(byte[] cfFileContent) {
		this.cvFileContent = cfFileContent;
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
		
		public UserBuilder setId(Long id) {
			user.id = id;
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
		
		public UserBuilder setCvFileContent(byte[] cvFileContent) {
			user.cvFileContent = cvFileContent;
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
