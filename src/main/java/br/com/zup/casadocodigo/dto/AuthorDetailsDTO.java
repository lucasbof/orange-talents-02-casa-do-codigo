package br.com.zup.casadocodigo.dto;

import java.io.Serializable;

import br.com.zup.casadocodigo.entities.Author;

public class AuthorDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;

	public AuthorDetailsDTO(Author author) {
		this.name = author.getName();
		this.description = author.getDescription();
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

}
