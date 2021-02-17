package br.com.zup.casadocodigo.controllers.responses;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import br.com.zup.casadocodigo.entities.Book;

public class BookDetailsResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String isbn;
	private Integer pagesTotal;
	private BigDecimal price;
	private String synopsis;
	private String index;
	private String publishDate;
	
	private AuthorDetailsResponse author;

	public BookDetailsResponse(Book book) {
		this.title = book.getTitle();
		this.isbn = book.getIsbn();
		this.pagesTotal = book.getPagesTotal();
		this.price = book.getPrice();
		this.synopsis = book.getSynopsis();
		this.index = book.getIndex();
		this.publishDate = book.getPublishDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.author = new AuthorDetailsResponse(book.getAuthor());
	}

	public String getTitle() {
		return title;
	}

	public String getIsbn() {
		return isbn;
	}

	public Integer getPagesTotal() {
		return pagesTotal;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public String getIndex() {
		return index;
	}

	public AuthorDetailsResponse getAuthor() {
		return author;
	}

	public String getPublishDate() {
		return publishDate;
	}
}
