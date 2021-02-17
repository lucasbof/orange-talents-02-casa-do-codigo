package br.com.zup.casadocodigo.controllers.validations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zup.casadocodigo.controllers.exceptions.FieldMessage;
import br.com.zup.casadocodigo.controllers.forms.ClientInsertForm;
import br.com.zup.casadocodigo.entities.CountryState;

public class ClientInsertFormValidator implements ConstraintValidator<ClientInsertFormValid, ClientInsertForm> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean isValid(ClientInsertForm form, ConstraintValidatorContext context) {
		if(form.getCountryId() == null) return true;
		
		List<FieldMessage> errorsList = new ArrayList<>();
		
		TypedQuery<CountryState> query = manager.createQuery("SELECT cs FROM CountryState cs INNER JOIN cs.country WHERE cs.country.id = :countryId", CountryState.class);
		query.setParameter("countryId", form.getCountryId());
		
		List<CountryState> resultList = query.getResultList();
		
		if(resultList.size() != 0) {
			if(form.getStateId() == null) {
				errorsList.add(new FieldMessage("stateId", "O país informado possuí estados vinculados, assim é obrigatório informar um ID desses estados"));
			}
			else {
				CountryState cs = new CountryState();
				cs.setId(form.getStateId());
				if(!resultList.contains(cs)) {
					errorsList.add(new FieldMessage("stateId", "Não foi encontrado um estado de ID " + form.getStateId() + " vinculado ao país de ID " + form.getCountryId()));
				}
			}
		}
		else if(form.getStateId() != null) {
			errorsList.add(new FieldMessage("stateId", "Não foi encontrado um estado de ID " + form.getStateId() + " vinculado ao país de ID " + form.getCountryId()));
		}
		
		for (FieldMessage e : errorsList) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
			.addConstraintViolation();
		}
				
		return errorsList.isEmpty();
	}


}
