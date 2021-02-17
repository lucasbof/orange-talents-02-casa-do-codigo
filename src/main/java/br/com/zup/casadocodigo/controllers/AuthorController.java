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

import br.com.zup.casadocodigo.controllers.forms.AuthorInsertForm;
import br.com.zup.casadocodigo.entities.Author;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Author> insert(@Valid @RequestBody AuthorInsertForm form) {
		Author author = form.toModel();
		manager.persist(author);
		return ResponseEntity.ok(author);
	}
}
