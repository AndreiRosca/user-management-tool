package com.endava.user.management.web.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

public class CreateUserForm {

	private String id;
	private String name;
	private String email;
	private String country;
	private String city;
	private String state;
	private String zipCode;
	private String gender;
	private List<String> frameworks = new ArrayList<>();
	private Part cvFile;
	
	public CreateUserForm() {
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

	public Part getCvFile() {
		return cvFile;
	}

	public void setCvFile(Part cvFile) {
		this.cvFile = cvFile;
	}

	@Override
	public String toString() {
		return "CreateUserForm [name=" + name + ", email=" + email + ", country=" + country + ", city=" + city + ", state="
				+ state + ", zipCode=" + zipCode + ", gender=" + gender + ", frameworks=" + frameworks + ", cvFile=" + cvFile
				+ "]";
	}
}
