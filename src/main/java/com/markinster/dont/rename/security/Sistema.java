package com.markinster.dont.rename.security;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.company.project.entity.Usuario;

@Named
@RequestScoped
public class Sistema implements Serializable {

	private static final long serialVersionUID = -4524668333882222457L;
	
	//pega uma instancia do usuario do logado
	public Usuario getUsuarioLogado() {
		UsuarioSistema usuarioSistema = getUsuarioSistema();
		if (usuarioSistema != null)
			return usuarioSistema.getUsuario();
		
		return null;
	}
	
	//pega o Usuario do Sistema do Spring
	private UsuarioSistema getUsuarioSistema() {
		UsuarioSistema user = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken)
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null)
			user = (UsuarioSistema) auth.getPrincipal(); //retorna o usuario logado
		
		return user;
	}

}
