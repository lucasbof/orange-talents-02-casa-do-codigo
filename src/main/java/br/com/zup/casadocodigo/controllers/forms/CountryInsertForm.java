package br.com.zup.casadocodigo.controllers.forms;

import javax.validation.constraints.NotBlank;

import br.com.zup.casadocodigo.controllers.validations.UniqueValue;
import br.com.zup.casadocodigo.entities.Country;

public class CountryInsertForm {
	
	@NotBlank
	@UniqueValue(domainClass = Country.class, fieldName = "name")
	private String name;

	public void setName(@NotBlank String name) {
		this.name = name;
	}

	public Country toModel() {
		return new Country(name);
	}

}
