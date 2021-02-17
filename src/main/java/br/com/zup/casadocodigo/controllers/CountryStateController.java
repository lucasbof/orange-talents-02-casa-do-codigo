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

import br.com.zup.casadocodigo.controllers.forms.CountryStateInsertForm;
import br.com.zup.casadocodigo.entities.CountryState;

@RestController
@RequestMapping(value = "/countrystates")
public class CountryStateController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<CountryState> insert(@Valid @RequestBody CountryStateInsertForm form) {
		CountryState state = form.toModel(manager);
		manager.persist(state);
		return ResponseEntity.ok(state);
	}
}
