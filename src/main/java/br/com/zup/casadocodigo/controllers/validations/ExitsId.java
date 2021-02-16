package br.com.zup.casadocodigo.controllers.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { ExistsIdValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExitsId {

	String message() default "Esta dependência não existe";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	Class<?> domainClass();
	
	String fieldName();
}
