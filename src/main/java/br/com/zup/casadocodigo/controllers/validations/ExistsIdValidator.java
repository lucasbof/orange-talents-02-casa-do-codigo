package br.com.zup.casadocodigo.controllers.validations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExistsIdValidator implements ConstraintValidator<ExitsId, Object> {

	private Class<?> domainClass;
	private String fieldName;

	/*
	 * Este obj será injetado pelo Spring se a anotação estiver fora da entidade,
	 * pois estaria no contexto do Spring, e assim funcionaria normalmente Caso
	 * esteja na entidade, o Hibernate que faria a injeção, mas ele não conseguiria
	 * fazer esta injeção, por não estar no contexto do Spring e assim, este objeto
	 * será nulo podendo lançar NullPointerException em isValid
	 */
	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(ExitsId params) {
		this.domainClass = params.domainClass();
		this.fieldName = params.fieldName();
	}

	@Override
	public boolean isValid(Object id, ConstraintValidatorContext context) {
		if(id == null) return true;
		Query query = manager.createQuery("SELECT 1 FROM " + domainClass.getName() + " WHERE " + fieldName + " = :id");
		query.setParameter("id", id);
		List<?> resultList = query.getResultList();
		
		Assert.state(resultList.size() <= 1, "Foi encontrado mais de um " + domainClass );
		
		return !resultList.isEmpty();
	}

}
