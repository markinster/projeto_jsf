package com.company.project.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.company.project.entity.Categoria;

@SuppressWarnings("unchecked")
public class Categorias implements Serializable {

	private static final long serialVersionUID = 4804420005855920026L;
	
	@Inject
	private EntityManager entityManager;
	
	public List<Categoria> raizes() {
		
		return entityManager.createQuery("from Categoria where categoriaPai is null", Categoria.class).getResultList();
		
	}

	public Categoria porId(Long id) {
		return entityManager.find(Categoria.class, id);
	}


	public List<Categoria> subcategoriasDe(Categoria categoriaPai) {
		return (List<Categoria>) entityManager.createQuery("from Categoria where categoriaPai = :catPai")
												.setParameter("catPai", categoriaPai)
												.getResultList();
	}

}
