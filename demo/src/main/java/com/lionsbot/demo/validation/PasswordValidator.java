package com.lionsbot.demo.validation;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		
		String regex = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@$!%*#?&])[A-Za-z[0-9]@$!%*#?&]{8,}$";
		return Pattern.compile(regex).matcher(password).matches();
		
	}

}
