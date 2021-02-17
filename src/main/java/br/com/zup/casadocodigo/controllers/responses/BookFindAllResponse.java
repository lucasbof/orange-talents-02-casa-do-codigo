package br.com.zup.casadocodigo.controllers.responses;

import java.io.Serializable;

public class BookFindAllResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String title;
	
	public BookFindAllResponse(Long id, String title) {
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
