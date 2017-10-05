package com.endava.user.management.web.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdateUserForm {

	@Size(min = 0)
	@Pattern(regexp = "\\d*")
	private String id = "";

	@NotEmpty
	@Size(min = 2)
	private String name;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	@Size(min = 3)
	private String country;

	@NotEmpty
	@Size(min = 2)
	private String city;

	@NotEmpty
	@Size(min = 2)
	private String state;

	@NotEmpty
	@Size(min = 2)
	private String zipCode;

	@NotEmpty
	@Pattern(regexp = "(Fem|M)ale")
	private String gender;

	@Valid
	@Size(min = 1)
	private List<@NotBlank String> frameworks = new ArrayList<>();

	@NotNull
	@Pattern(regexp = CreateUserForm.DATE_PATTERN)
	private String birthDate;

	private UpdateUserForm(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.email = builder.email;
		this.country = builder.country;
		this.city = builder.city;
		this.state = builder.state;
		this.zipCode = builder.zipCode;
		this.gender = builder.gender;
		this.frameworks = builder.frameworks;
		this.birthDate = builder.birthDate;
	}
	
	public UpdateUserForm() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<String> getFrameworks() {
		return frameworks;
	}

	public void setFrameworks(List<String> frameworks) {
		this.frameworks = frameworks;
	}
	
	public void addFramework(String framework) {
		frameworks.add(framework);
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "CreateUserForm [name=" + name + ", email=" + email + ", country=" + country + ", city=" + city + ", state="
				+ state + ", zipCode=" + zipCode + ", gender=" + gender + ", frameworks=" + frameworks
				+ "]";
	}

	public static Builder newBuiler() {
		return new Builder();
	}

	public static final class Builder {
		private String id;
		private String name;
		private String email;
		private String country;
		private String city;
		private String state;
		private String zipCode;
		private String gender;
		private List<String> frameworks = new ArrayList<>();
		private String birthDate;

		private Builder() {
		}

		public Builder setId(String id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder setCountry(String country) {
			this.country = country;
			return this;
		}

		public Builder setCity(String city) {
			this.city = city;
			return this;
		}

		public Builder setState(String state) {
			this.state = state;
			return this;
		}

		public Builder setZipCode(String zipCode) {
			this.zipCode = zipCode;
			return this;
		}

		public Builder setGender(String gender) {
			this.gender = gender;
			return this;
		}

		public Builder setFrameworks(List<String> frameworks) {
			this.frameworks = frameworks;
			return this;
		}

		public Builder setBirthDate(String birthDate) {
			this.birthDate = birthDate;
			return this;
		}

		public Builder addFramework(String framework) {
			frameworks.add(framework);
			return this;
		}
	}
}