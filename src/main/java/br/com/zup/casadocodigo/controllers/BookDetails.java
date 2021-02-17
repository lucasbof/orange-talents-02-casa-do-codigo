package br.com.zup.casadocodigo.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigo.controllers.exceptions.customized.ResourceNotFoundException;
import br.com.zup.casadocodigo.controllers.responses.BookDetailsResponse;
import br.com.zup.casadocodigo.entities.Book;

@RestController
@RequestMapping(value = "/bookdetails")
public class BookDetails {

	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping(value = "/{id}")
	@Transactional(readOnly = true)
	public ResponseEntity< BookDetailsResponse> findById(@PathVariable Long id) {
		Book book = manager.find(Book.class, id);
		if(book == null) throw new ResourceNotFoundException("Não foi encontrado um livro para o ID informado");
		return ResponseEntity.ok(new BookDetailsResponse(book));
	}
}
