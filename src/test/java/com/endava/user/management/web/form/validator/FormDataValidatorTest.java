package com.endava.user.management.web.form.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Arrays;

import javax.servlet.http.Part;

import org.junit.Before;
import org.junit.Test;

import com.endava.user.management.web.form.CreateUserForm;

public class FormDataValidatorTest {

	CreateUserForm userForm;
	FormDataValidator<CreateUserForm> validator;
	
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
				.setCvFile(mock(Part.class))
				.addFramework("Spring Core")
				.build();
	}
	
	@Test
	public void validatorDoesNotAcceptNullName() {
		userForm.setName(null);
		validateUserForm();
		assertFalse(validator.isValid());
	}
	
	@Test
	public void validatorAcceptsValidForm() {
		validateUserForm();
		assertTrue(validator.isValid());
	}
	
	@Test
	public void validatorDoesNotAcceptFormsWithShortNames() {
		userForm.setName("a");
		validateUserForm();
		assertFalse(validator.isValid());
	}
	
	@Test
	public void validatorAcceptsCollections() {
		validateUserForm();
		assertTrue(validator.isValid());
	}

	@Test
	public void validatorRejectsEmptyCollections() {
		userForm.setFrameworks(Arrays.asList());
		validateUserForm();
		assertFalse(validator.isValid());
	}
	
	@Test
	public void validatorRejectsCollectionsWithEmptyStrings() {
		userForm.setFrameworks(Arrays.asList(""));
		validateUserForm();
		assertFalse(validator.isValid());
	}
	
	@Test
	public void validatorRejectsNonNumericIds() {
		userForm.setId("a");
		validateUserForm();
		assertFalse(validator.isValid());
	}
	
	@Test
	public void validatorAcceptsNumericIds() {
		userForm.setId("134354");
		validateUserForm();
		assertTrue(validator.isValid());
	}
	
	private void validateUserForm() {
	 validator = new FormDataValidator<CreateUserForm>(userForm);
	 validator.validate();
	}
}
