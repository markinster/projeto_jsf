package com.company.project.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.company.project.entity.Grupo;
import com.company.project.entity.Usuario;
import com.company.project.repository.Grupos;
import com.company.project.repository.Usuarios;
import com.company.project.repository.filter.UsuarioFilter;
import com.markinster.dont.rename.util.jpa.Transactional;
import com.markinster.dont.rename.util.jsf.BusinessException;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = -5961127585708537455L;

	@Inject
	private Usuarios usuarios;
	
	@Inject
	private Grupos grupos;

	public List<Usuario> pesquisar(UsuarioFilter filter) {
		return usuarios.filtrados(filter);
	}
	
	@Transactional
	public void salvar(Usuario usuario) {
		Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());
		if (usuarioExistente == null || usuario.equals(usuarioExistente))
			usuarios.guardar(usuario);
		else
			throw new BusinessException("Ja existe um usuario cadastrado com este email.");
	}
	
	@Transactional
	public void excluir(Usuario usuario) {
		usuarios.remover(usuario);
	}

	public List<Grupo> getGrupos() {
		return grupos.filtrados();
	}
}
