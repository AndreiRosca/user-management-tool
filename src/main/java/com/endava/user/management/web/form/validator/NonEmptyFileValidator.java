package com.endava.user.management.web.form.validator;

import javax.servlet.http.Part;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NonEmptyFileValidator implements ConstraintValidator<NonEmptyFile, Part> {

	@Override
	public boolean isValid(Part filePart, ConstraintValidatorContext context) {
		return !(filePart.getSize() == 0 && "".equals(filePart.getSubmittedFileName()));
	}
}
