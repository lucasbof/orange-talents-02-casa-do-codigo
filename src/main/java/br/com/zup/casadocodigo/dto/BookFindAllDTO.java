package br.com.zup.casadocodigo.dto;

import java.io.Serializable;

public class BookFindAllDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	
	public BookFindAllDTO(Long id, String title) {
		this.id = id;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}
	
}
