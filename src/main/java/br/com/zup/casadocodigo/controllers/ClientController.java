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

import br.com.zup.casadocodigo.controllers.forms.ClientInsertForm;
import br.com.zup.casadocodigo.entities.Client;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping
	@Transactional
	public ResponseEntity<Client> insert(@Valid @RequestBody ClientInsertForm form) {
		Client client = form.toModel(manager);
		manager.persist(client);
		return ResponseEntity.ok(client);
	}

}
