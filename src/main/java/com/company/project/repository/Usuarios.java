package com.company.project.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.company.project.entity.Grupo;
import com.company.project.entity.Usuario;
import com.company.project.repository.filter.UsuarioFilter;
import com.markinster.dont.rename.util.jsf.BusinessException;

import static com.company.project.utils.StringUtils.isEmpty;

@SuppressWarnings("unchecked")
public class Usuarios implements Serializable {

	private static final long serialVersionUID = -7200371807580783128L;

	@Inject
	private EntityManager manager;	

	public List<Usuario> filtrados(UsuarioFilter filter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);
		
		if (!isEmpty(filter.getNome()))
			criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
		
		if (!isEmpty(filter.getEmail()))
			criteria.add(Restrictions.ilike("email", filter.getEmail(), MatchMode.ANYWHERE));
		
		return criteria.list();
	}
	
	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);
	}
	
	public Usuario porEmail(String email) {
		try {
			Session session = manager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Usuario.class);
			criteria.add(Restrictions.ilike("email", email, MatchMode.EXACT));
			return (Usuario) criteria.uniqueResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void remover(Usuario usuario) {
		try {
			usuario = manager.find(Usuario.class, usuario.getId());
			manager.remove(usuario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new BusinessException("Não foi possivel excluir este usuario." + e.getMessage());
		}
	}	
	
	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}

	public List<Grupo> todosOsGruposDeUsuarios() {
		Query q = manager.createQuery("from Grupo", Grupo.class);
		return q.getResultList();
	}

	public List<Usuario> vendedores() {
		return filtrados(new UsuarioFilter());
	}
	
	
}
