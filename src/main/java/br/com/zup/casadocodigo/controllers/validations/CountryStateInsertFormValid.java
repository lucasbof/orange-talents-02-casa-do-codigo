package br.com.zup.casadocodigo.controllers.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { CountryStateInsertFormValidator.class })
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface CountryStateInsertFormValid {
	
	String message() default "Erro de Validação";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
}
