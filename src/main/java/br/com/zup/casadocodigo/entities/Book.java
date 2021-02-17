package br.com.zup.casadocodigo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_book")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String title;

	@Column(nullable = false, length = 500)
	private String synopsis;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String index;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private Integer pagesTotal;

	@Column(nullable = false, unique = true)
	private String isbn;

	@Column(nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate publishDate;

	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@Deprecated
	public Book() {
	}

	public Book(String title, String synopsis, String index, BigDecimal price, Integer pagesTotal, String isbn,
			LocalDate publishDate, Author author, Category category) {
		this.title = title;
		this.synopsis = synopsis;
		this.index = index;
		this.price = price;
		this.pagesTotal = pagesTotal;
		this.isbn = isbn;
		this.publishDate = publishDate;
		this.author = author;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	// O ID é opcional, por isso existe este setter
	public void setId(Long id) {
		this.id = id;
	}

	public String getIndex() {
		return index;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTitle() {
		return title;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Integer getPagesTotal() {
		return pagesTotal;
	}

	public String getIsbn() {
		return isbn;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public Author getAuthor() {
		return author;
	}

	public Category getCategory() {
		return category;
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
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
