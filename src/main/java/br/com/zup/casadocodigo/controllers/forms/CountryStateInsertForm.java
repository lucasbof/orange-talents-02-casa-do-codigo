package br.com.zup.casadocodigo.controllers.forms;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.zup.casadocodigo.controllers.validations.CountryStateInsertFormValid;
import br.com.zup.casadocodigo.controllers.validations.ExitsId;
import br.com.zup.casadocodigo.entities.Country;
import br.com.zup.casadocodigo.entities.CountryState;

@CountryStateInsertFormValid
public class CountryStateInsertForm  implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String name;
	
	@NotNull
	@ExitsId(domainClass = Country.class, fieldName = "id")
	private Long countryId;

	public CountryStateInsertForm(@NotBlank String name, @NotNull Long countryId) {
		this.name = name;
		this.countryId = countryId;
	}

	public String getName() {
		return name;
	}

	public Long getCountryId() {
		return countryId;
	}

	public CountryState toModel(EntityManager manager) {
		@NotNull Country country = manager.find(Country.class, countryId);
		Assert.state(country != null, "O país do estado não foi encontrado");
		return new CountryState(name, country);
	}
	
	
	
	
}
