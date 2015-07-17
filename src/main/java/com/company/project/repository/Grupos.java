package com.company.project.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.company.project.entity.Grupo;

@SuppressWarnings("unchecked")
public class Grupos implements Serializable {

	private static final long serialVersionUID = 4527816893272405644L;
	
	@Inject
	private EntityManager manager;
	
	public List<Grupo> filtrados() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Grupo.class);
		return criteria.list();
	} 
	
	public Grupo porId(Long id) {
		return manager.find(Grupo.class, id);
	}

}
