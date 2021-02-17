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

import br.com.zup.casadocodigo.controllers.forms.CategoryInsertForm;
import br.com.zup.casadocodigo.entities.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Category> insert(@Valid @RequestBody CategoryInsertForm form) {
		Category category = form.toModel();
		manager.persist(category);
		return ResponseEntity.ok(category);
	}
}
