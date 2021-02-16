package br.com.zup.casadocodigo.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigo.controllers.forms.BookInsertForm;
import br.com.zup.casadocodigo.entities.Book;

@RestController
@RequestMapping(value = "/books")
public class BookController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Book> insert(@Valid @RequestBody BookInsertForm form) {
		Book book = form.toModel(manager);
		
		manager.persist(book);
		
		return ResponseEntity.ok(book);
	}
}
