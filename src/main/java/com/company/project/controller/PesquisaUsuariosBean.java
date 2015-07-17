package com.company.project.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.company.project.entity.Usuario;
import com.company.project.repository.filter.UsuarioFilter;
import com.company.project.service.CadastroUsuarioService;
import com.markinster.dont.rename.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUsuarioService service;
	
	private Usuario usuario;
	
	private String nome;
	private String email;
	
	private List<Usuario> listagem;
	
	public List<Usuario> getListagem() {
		if (!FacesUtil.isPostback())
			pesquisar();
		
		return listagem;
	}
	
	public void setListagem(List<Usuario> listagem) {
		this.listagem = listagem;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void excluir() {
		service.excluir(usuario);
		pesquisar();
	}
	
	public void pesquisar() {
		UsuarioFilter filter = new UsuarioFilter();
		filter.setEmail(email);
		filter.setNome(nome);
		
		listagem = service.pesquisar(filter);
	}

}
