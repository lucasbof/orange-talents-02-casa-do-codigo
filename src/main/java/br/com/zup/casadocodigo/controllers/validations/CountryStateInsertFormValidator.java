package br.com.zup.casadocodigo.controllers.validations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

import br.com.zup.casadocodigo.controllers.exceptions.FieldMessage;
import br.com.zup.casadocodigo.controllers.forms.CountryStateInsertForm;

public class CountryStateInsertFormValidator  implements ConstraintValidator<CountryStateInsertFormValid, CountryStateInsertForm> {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean isValid(CountryStateInsertForm form, ConstraintValidatorContext context) {
		if(form.getName() == null || form.getCountryId() == null) {
			return true;
		}
		
		List<FieldMessage> list = new ArrayList<>();
		
		Query query = manager.createQuery("SELECT 1 FROM CountryState cs INNER JOIN cs.country WHERE LOWER(cs.name) = LOWER(:name) AND cs.country.id = :countryId");
		query.setParameter("name", form.getName().trim());
		query.setParameter("countryId", form.getCountryId());
		
		List<?> resultList = query.getResultList();
		
		Assert.state(resultList.size() <= 1, "Foi encontrado mais de um estado " + form.getName() + " para o país de ID " + form.getCountryId() );
		
		if(!resultList.isEmpty()) {
			list.add(new FieldMessage("name", "O nome " + form.getName() + " já existe para o país de ID " + form.getCountryId()));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
