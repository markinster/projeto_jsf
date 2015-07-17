package com.company.project.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.company.project.entity.Categoria;
import com.company.project.repository.Categorias;
import com.markinster.dont.rename.util.cdi.CDIServiceLocator;

@FacesConverter (forClass = Categoria.class, value = "categoriaConverter")
public class CategoriaConverter implements Converter {
	
	private Categorias categorias;
	
	//forca uma injecao de dependencia
	public CategoriaConverter() {
		categorias =  CDIServiceLocator.getBean(Categorias.class);
	}
	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Categoria categoria = null;
		if (value != null) {
			Long id = Long.valueOf(value);			
			categoria = categorias.porId(id);
		}
		
		return categoria;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value == null)
			return "";
		
		Categoria categoria = (Categoria) value;
		
		return categoria.getId() + "";
	}

}
