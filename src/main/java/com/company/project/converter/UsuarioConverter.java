package com.company.project.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.company.project.entity.Produto;
import com.company.project.entity.Usuario;
import com.company.project.repository.Usuarios;
import com.markinster.dont.rename.util.cdi.CDIServiceLocator;

@FacesConverter (forClass = Produto.class, value = "usuarioConverter")
public class UsuarioConverter implements Converter {
	
	private Usuarios usuarios;
	
	//forca uma injecao de dependencia
	public UsuarioConverter() {
		usuarios =  CDIServiceLocator.getBean(Usuarios.class);
	}
	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Usuario usuario = null;
		if (value != null) {
			Long id = Long.valueOf(value);			
			usuario = usuarios.porId(id);
		}
		
		return usuario;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value == null)
			return "";
		
		Usuario usuario = (Usuario) value;
		
		return usuario.getId() + "";
	}

}
