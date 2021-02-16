package br.com.zup.casadocodigo.controllers.validations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

	private String fieldName;
	private Class<?> domainClass;
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public void initialize(UniqueValue params) {
		this.fieldName = params.fieldName();
		this.domainClass = params.domainClass();	
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("SELECT 1 FROM " + domainClass.getName() + " WHERE " + fieldName + " = :value");
		query.setParameter("value", value);
		List<?> resultList = query.getResultList();
		
		Assert.state(resultList.size() <= 1, "Foi encontrado mais de um " + domainClass + "com o atributo " + fieldName );
		
		return resultList.isEmpty();
	}
	
	



}
