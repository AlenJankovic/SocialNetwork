package com.examen.validation;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
@Documented
public @interface PasswordMatch {
		String message() default "{error.password.missmatch}"; 	
		
		Class<?>[] groups() default {};			// must have according hibernate to create custom annotation
		
		Class<? extends Payload>[] payload() default {};	// must have according hibernate to create custom annotation
}
