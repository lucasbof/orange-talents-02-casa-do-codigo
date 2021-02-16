package br.com.zup.casadocodigo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigo.controllers.forms.AuthorInsertForm;
import br.com.zup.casadocodigo.entities.Author;
import br.com.zup.casadocodigo.repositories.AuthorRepository;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

	@Autowired
	private AuthorRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Author> insert(@Valid @RequestBody AuthorInsertForm form) {
		Author author = form.toModel();
		
		author = repository.save(author);
		
		return ResponseEntity.ok(author);
	}
}
