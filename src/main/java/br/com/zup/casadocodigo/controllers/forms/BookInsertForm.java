package br.com.zup.casadocodigo.controllers.forms;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.zup.casadocodigo.controllers.validations.ExitsId;
import br.com.zup.casadocodigo.controllers.validations.UniqueValue;
import br.com.zup.casadocodigo.entities.Author;
import br.com.zup.casadocodigo.entities.Book;
import br.com.zup.casadocodigo.entities.Category;

public class BookInsertForm {

	@NotBlank
	@UniqueValue(domainClass = Book.class, fieldName = "title", message = "Este titulo já existe")
	private String title;

	@NotBlank
	@Size(max = 500, message = "O resumo deve ter até 500 caracteres")
	private String synopsis;

	@NotBlank
	private String index;

	@Min(value = 20, message = "O preço deve ser maior ou igual a R$ 20.00")
	@NotNull
	private BigDecimal price;

	@NotNull
	@Min(value = 100, message = "O número total de páginas deve ser maior ou igual 100")
	private Integer pagesTotal;

	@NotBlank
	@UniqueValue(domainClass = Book.class, fieldName = "isbn", message = "Este isbn já existe")
	private String isbn;

	@NotNull
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
	private LocalDate creationDate;

	@NotNull
	@ExitsId(domainClass = Author.class, fieldName = "id", message = "Não foi encontrado um autor com o ID informado")
	private Long authorId;

	@NotNull
	@ExitsId(domainClass = Category.class, fieldName = "id", message = "Não foi encontrado uma categoria com o ID informado")
	private Long categoryId;

	public BookInsertForm(@NotBlank String title, @NotBlank @Size(max = 500) String synopsis, @NotBlank String index,
			@Min(20) @NotNull BigDecimal price, @NotNull @Min(100) Integer pagesTotal, @NotBlank String isbn, 
			@NotNull Long authorId, @NotNull Long categoryId) {
		this.title = title;
		this.synopsis = synopsis;
		this.index = index;
		this.price = price;
		this.pagesTotal = pagesTotal;
		this.isbn = isbn;
		this.authorId = authorId;
		this.categoryId = categoryId;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public Book toModel(EntityManager manager) {
		@NotNull Author author = manager.find(Author.class, this.authorId);
		@NotNull Category category = manager.find(Category.class, this.categoryId);
		
		Assert.state(author != null, "O autor do livro não foi encontrado");
		Assert.state(category != null, "A categoria do livro não foi encontrada");
		
		return new Book(title, synopsis, index, price, pagesTotal, isbn, creationDate, author, category);
	}

}
