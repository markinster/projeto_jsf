package com.company.project.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.company.project.entity.Produto;
import com.company.project.repository.filter.ProdutoFilter;
import com.markinster.dont.rename.util.jsf.BusinessException;

import static com.company.project.utils.StringUtils.isEmpty;

@SuppressWarnings("unchecked")
public class Produtos implements Serializable {

	private static final long serialVersionUID = -3905371432870924941L;
	
	@Inject
	private EntityManager manager;

	public List<Produto> todos() {
		return manager.createQuery("from Produto", Produto.class).getResultList();
	}

	public Produto guardar(Produto produto) {
		return manager.merge(produto);
	}

	public Produto porSKU(String sku) {
		try {
			return manager.createQuery("from Produto where upper(sku) like :sku", Produto.class)
					.setParameter("sku", sku.toUpperCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Produto> filtrados(ProdutoFilter filter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);
		
		if (!isEmpty(filter.getSku()))
			criteria.add(Restrictions.eq("sku", filter.getSku()));
		
		if (!isEmpty(filter.getNome()))
			criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
		
		return criteria.list();
	}
	

	public Produto porId(Long id) {
		return manager.find(Produto.class, id);
	}
	
	public void remover(Produto produto) {
		try {
			produto = porId(produto.getId());
			manager.remove(produto);
			manager.flush();
		} catch (PersistenceException e) {
			throw new BusinessException("Produto não pode ser excluido. " + e.getMessage() );
		}
	}
}
