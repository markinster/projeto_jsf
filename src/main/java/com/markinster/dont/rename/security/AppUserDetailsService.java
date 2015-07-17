package com.markinster.dont.rename.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.company.project.entity.Grupo;
import com.company.project.entity.Usuario;
import com.company.project.repository.Usuarios;
import com.markinster.dont.rename.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {
	

	@Override
	public UserDetails loadUserByUsername(String login)	throws UsernameNotFoundException {
		Usuarios usuarios = CDIServiceLocator.getBean(Usuarios.class);
		Usuario usuario = usuarios.porEmail(login);
		
		UsuarioSistema usuarioSistema = null;
		if (usuario != null) {
			usuarioSistema = new UsuarioSistema(usuario, getGrupos(usuario));
		}
		return usuarioSistema;
	}

	
	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		for(Grupo grupo : usuario.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}
		
		return authorities;
	}

}
