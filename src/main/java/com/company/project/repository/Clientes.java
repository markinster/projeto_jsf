package com.company.project.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.company.project.entity.Cliente;
import com.company.project.repository.filter.ClienteFilter;
import com.markinster.dont.rename.util.jsf.BusinessException;

@SuppressWarnings("unchecked")
public class Clientes implements Serializable {

	private static final long serialVersionUID = 6633562006470930174L;

	@Inject
	private EntityManager manager;

	public void guarda(Cliente cliente) {
		manager.merge(cliente);
	}

	public Cliente porId(Long id) {
		try {
			return manager.find(Cliente.class, id);
		} catch (Exception e) {
			return null;
		}
	}

	public List<Cliente> filtrados(ClienteFilter f) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);

		if (!isEmpty(f.getNome()))
			criteria.add(Restrictions.ilike("nome", f.getNome(), MatchMode.ANYWHERE));

		if (!isEmpty(f.getEmail()))
			criteria.add(Restrictions.ilike("email", f.getEmail(), MatchMode.ANYWHERE));

		return criteria.list();
	}

	private boolean isEmpty(String string) {
		if (string == null)
			return true;

		if (string.trim().isEmpty())
			return true;

		return false;
	}

	public void remover(Cliente cliente) {
		try {
			cliente = manager.find(Cliente.class, cliente.getId());
			manager.remove(cliente);
			manager.flush();
		} catch (PersistenceException e) {
			throw new BusinessException("Não foi possivel excluir este cliente." + e.getMessage());
		}
	}

}
