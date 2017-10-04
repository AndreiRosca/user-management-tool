package com.endava.user.management.web.form.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class FormDataValidator<T> {

	private final T validatedObject;
	private final Validator validator;
	private Set<ConstraintViolation<T>> constraintViolations;

	public FormDataValidator(T validatedObject) {
		this.validatedObject = validatedObject;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	public void validate() {
		constraintViolations = validator.validate(validatedObject);
	}

	public boolean isValid() {
		return constraintViolations.isEmpty();
	}
	
	public boolean isInvalid() {
		return !isValid();
	}
}
