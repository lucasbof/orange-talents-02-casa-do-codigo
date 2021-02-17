package br.com.zup.casadocodigo.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "tb_client")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false, unique = true)
	private String document;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String complement;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String cep;
	
	@Column(nullable = false)
	private String phone;
	
	@OneToOne
	@JoinColumn(name = "country_id", nullable = false)
	private Country country;
	
	@OneToOne
	@JoinColumn(name = "state_id")
	@JsonInclude(Include.NON_NULL)
	private CountryState state;

	public Client(String name, String lastName, String document, String email, String address, String complement,
			String city, String cep, String phone, Country country, CountryState state) {
		this.name = name;
		this.lastName = lastName;
		this.document = document;
		this.email = email;
		this.address = address;
		this.complement = complement;
		this.city = city;
		this.cep = cep;
		this.phone = phone;
		this.country = country;
		this.state = state;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
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

	public Country getCountry() {
		return country;
	}

	public CountryState getState() {
		return state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
