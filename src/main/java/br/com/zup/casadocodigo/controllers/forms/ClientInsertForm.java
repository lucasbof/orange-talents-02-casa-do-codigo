package br.com.zup.casadocodigo.controllers.forms;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.zup.casadocodigo.controllers.validations.CpfOrCnpj;
import br.com.zup.casadocodigo.controllers.validations.ClientInsertFormValid;
import br.com.zup.casadocodigo.controllers.validations.ExitsId;
import br.com.zup.casadocodigo.controllers.validations.UniqueValue;
import br.com.zup.casadocodigo.entities.Client;
import br.com.zup.casadocodigo.entities.Country;
import br.com.zup.casadocodigo.entities.CountryState;

@ClientInsertFormValid
public class ClientInsertForm {

	@NotBlank
	private String name;

	@NotBlank
	private String lastName;

	@NotBlank
	@UniqueValue(domainClass = Client.class, fieldName = "document")
	@CpfOrCnpj
	private String document;

	@NotBlank
	@UniqueValue(domainClass = Client.class, fieldName = "email")
	@Email
	private String email;

	@NotBlank
	private String address;

	@NotBlank
	private String complement;

	@NotBlank
	private String city;

	@NotBlank
	private String cep;

	@NotBlank
	private String phone;

	@NotNull
	@ExitsId(domainClass = Country.class, fieldName = "id", message = "Não foi encontrado um país para o ID informado")
	private Long countryId;

	private Long stateId;

	public ClientInsertForm(@NotBlank String name, @NotBlank String lastName, @NotBlank String document,
			@NotBlank @Email String email, @NotBlank String address, @NotBlank String complement, @NotBlank String city,
			@NotBlank String cep, @NotBlank String phone, @NotNull Long countryId, Long stateId) {
		super();
		this.name = name.trim();
		this.lastName = lastName.trim();
		this.document = document.trim().replaceAll("[^0-9]", "");
		this.email = email.trim();
		this.address = address.trim();
		this.complement = complement.trim();
		this.city = city.trim();
		this.cep = cep.trim();
		this.phone = phone.trim();
		this.countryId = countryId;
		this.stateId = stateId;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDocument() {
		return document;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getComplement() {
		return complement;
	}

	public String getCity() {
		return city;
	}

	public String getCep() {
		return cep;
	}

	public String getPhone() {
		return phone;
	}

	public Long getCountryId() {
		return countryId;
	}

	public Long getStateId() {
		return stateId;
	}

	public Client toModel(EntityManager manager) {
		@NotNull Country country = manager.find(Country.class, this.countryId);
		CountryState state = null;
		if(this.stateId != null) state = manager.find(CountryState.class, this.stateId);
		
		Assert.state(country != null, "O país do cliente não foi encontrado");
		
		return new Client(name, lastName, document, email, address, complement, city, cep, phone, country, state);
	}

}
