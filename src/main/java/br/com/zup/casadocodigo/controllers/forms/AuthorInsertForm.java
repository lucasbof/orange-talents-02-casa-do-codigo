package br.com.zup.casadocodigo.controllers.forms;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.casadocodigo.controllers.validations.UniqueValue;
import br.com.zup.casadocodigo.entities.Author;

public class AuthorInsertForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String name;

	@Email
	@NotBlank
	@UniqueValue(domainClass = Author.class, fieldName = "email", message = "Este email já existe!")
	private String email;

	@Size(max = 400)
	@NotBlank
	private String description;
	
	@Deprecated
	public AuthorInsertForm() {
	}

	public AuthorInsertForm(@NotBlank String name, @Email @NotBlank String email,
			@Size(max = 400) @NotBlank String description) {
		this.name = name;
		this.email = email;
		this.description = description;
	}
	
	public Author toModel() {
		return new Author(this.name, this.email, this.description);
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getDescription() {
		return description;
	}

}
