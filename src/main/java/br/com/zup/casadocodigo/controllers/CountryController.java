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

import br.com.zup.casadocodigo.controllers.forms.CountryInsertForm;
import br.com.zup.casadocodigo.entities.Country;

@RestController
@RequestMapping(value = "/countries")
public class CountryController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Country> insert(@Valid @RequestBody CountryInsertForm form) {
		Country country = form.toModel();
		manager.persist(country);
		return ResponseEntity.ok(country);
	}
}
