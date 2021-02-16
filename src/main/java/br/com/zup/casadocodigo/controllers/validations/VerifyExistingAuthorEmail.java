package br.com.zup.casadocodigo.controllers.validations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.casadocodigo.controllers.forms.AuthorInsertForm;
import br.com.zup.casadocodigo.entities.Author;
import br.com.zup.casadocodigo.repositories.AuthorRepository;

@Component
public class VerifyExistingAuthorEmail implements Validator {
	
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return AuthorInsertForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) return;
		AuthorInsertForm form = (AuthorInsertForm) target;
		Optional<Author> authorOpt = authorRepository.findByEmail(form.getEmail());
		if(authorOpt.isPresent()) {
			errors.rejectValue("email", null, "O email " + form.getEmail() + " já existe!");
		}
	}



}
