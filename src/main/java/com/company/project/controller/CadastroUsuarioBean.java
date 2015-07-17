package com.company.project.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.company.project.entity.Grupo;
import com.company.project.entity.Usuario;
import com.company.project.service.CadastroUsuarioService;
import com.markinster.dont.rename.util.jsf.BusinessException;
import com.markinster.dont.rename.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroUsuarioService service;
	
	private List<Grupo> grupos = new ArrayList<Grupo>();
	
	private Grupo grupo;
	
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public List<Grupo> getGrupos() {
		if (!FacesUtil.isPostback())
			grupos =  service.getGrupos();
		
		return grupos;
	}
		
	public void salvar() {
		service.salvar(usuario);
		limpaFormulario();
		FacesUtil.addInfoMessage("Usuário salvo com sucesso");
	}

	private void limpaFormulario() {
		usuario = new Usuario();
		
	}

	//adiciona na lista
	public void addGrupo() {
		if (grupo == null)
			throw new BusinessException("Selecione o grupo que deseja adicionar");
		
		
		if (usuario.getGrupos() == null)
			usuario.setGrupos(new ArrayList<Grupo>());
		
		if (usuario.getGrupos().contains(grupo))
			throw new BusinessException("Este grupo já foi adicionado para este usuario.");
		
		usuario.getGrupos().add(grupo);
		
	}
	
	//apenas remove da Lista, quando salvar a remoção real será feira
	public void excluir () {
		usuario.getGrupos().remove(grupo);
	}
	
	

}
