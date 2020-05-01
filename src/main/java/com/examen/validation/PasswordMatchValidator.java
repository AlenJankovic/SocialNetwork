package com.examen.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.examen.model.SiteUser;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, SiteUser> {

	@Override
	public boolean isValid(SiteUser user, ConstraintValidatorContext c) {
		
		String plainPassword = user.getPlainPassword();
		String repeatPassword= user.getRepeatPassword();
		
		if(plainPassword == null || plainPassword.length() == 0) {  //not comming from registration form
			return true;
		}
		if(plainPassword == null || !plainPassword.equals(repeatPassword)) {
			return false;
		}
		
		
		return true;
	}



}
