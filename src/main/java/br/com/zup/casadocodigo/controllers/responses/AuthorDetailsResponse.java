package br.com.zup.casadocodigo.controllers.responses;

import java.io.Serializable;

import br.com.zup.casadocodigo.entities.Author;

public class AuthorDetailsResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;

	public AuthorDetailsResponse(Author author) {
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
