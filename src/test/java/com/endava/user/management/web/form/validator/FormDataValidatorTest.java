package com.endava.user.management.web.form.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.endava.user.management.web.form.CreateUserForm;

public class FormDataValidatorTest {

	CreateUserForm userForm;
	
	@Before
	public void setUp() {
		userForm = CreateUserForm.newBuiler()
				.setCity("Los angeles")
				.setCountry("USA")
				.setState("California")
				.setZipCode("12121")
				.setEmail("mike@gmail.com")
				.setGender("Male")
				.setName("Mike Smith")
				.build();
	}
	
	@Test
	public void validatorDoesNotAcceptNullName() {
		userForm.setName(null);
		FormDataValidator<CreateUserForm> validator = new FormDataValidator<CreateUserForm>(userForm);
		validator.validate();
		assertFalse(validator.isValid());
	}
	
	@Test
	public void validatorAcceptsValidForm() {
		FormDataValidator<CreateUserForm> validator = new FormDataValidator<CreateUserForm>(userForm);
		validator.validate();
		assertTrue(validator.isValid());
	}
	
	@Test
	public void validatorDoesNotAcceptFormsWithShortNames() {
		userForm.setName("a");
		FormDataValidator<CreateUserForm> validator = new FormDataValidator<CreateUserForm>(userForm);
		validator.validate();
		assertFalse(validator.isValid());
	}
}
