package br.com.zup.casadocodigo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigo.controllers.forms.CategoryInsertForm;
import br.com.zup.casadocodigo.entities.Category;
import br.com.zup.casadocodigo.repositories.CategoryRepository;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

	@Autowired
	private CategoryRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Category> insert(@Valid @RequestBody CategoryInsertForm form) {
		Category category = form.toModel();
		category = repository.save(category);
		return ResponseEntity.ok(category);
	}
}
