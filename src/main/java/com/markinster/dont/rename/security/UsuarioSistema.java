package com.markinster.dont.rename.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.company.project.entity.Usuario;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1621548356565977983L;
	
	private Usuario usuario;

	public UsuarioSistema(Usuario usuario,	Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

}
