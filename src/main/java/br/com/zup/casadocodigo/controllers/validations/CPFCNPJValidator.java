package br.com.zup.casadocodigo.controllers.validations;

import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class CPFCNPJValidator implements ConstraintValidator<CPFCNPJ, String> {

	@Override
	public boolean isValid(String document, ConstraintValidatorContext context) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		if(document != null) {
			if(document.length() < 12) {
				Set<ConstraintViolation<TestCPF>> cpfViolations = validator.validate(new TestCPF(document));
				if(cpfViolations.size() != 0) {
					return false;
				}
			}
			else {
				Set<ConstraintViolation<TestCNPJ>> cnpjViolations = validator.validate(new TestCNPJ(document));
				if(cnpjViolations.size() != 0) {
					return false;
				}
			}
		}
		return true;
	}

	class TestCPF {
		@CPF
		private String cpf;

		public TestCPF(String cpf) {
			this.cpf = cpf;
		}
	}
	
	class TestCNPJ {
		@CNPJ
		private String cnpj;

		public TestCNPJ(String cnpj) {
			this.cnpj = cnpj;
		}
	}

}
