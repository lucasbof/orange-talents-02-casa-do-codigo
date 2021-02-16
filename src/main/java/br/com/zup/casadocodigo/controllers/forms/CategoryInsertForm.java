package br.com.zup.casadocodigo.controllers.forms;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.zup.casadocodigo.controllers.validations.UniqueValue;
import br.com.zup.casadocodigo.entities.Category;

public class CategoryInsertForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@UniqueValue(domainClass = Category.class, fieldName = "name", message = "Este name já existe!")
	@NotBlank
	private String name;
	
	public CategoryInsertForm() {
	}

	public CategoryInsertForm(@NotBlank String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public Category toModel() {
		return new Category(this.name);
	}
	
	
}
