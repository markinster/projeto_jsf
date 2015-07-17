package com.company.project.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.company.project.entity.Grupo;
import com.company.project.repository.Grupos;
import com.markinster.dont.rename.util.cdi.CDIServiceLocator;

@FacesConverter (forClass = Grupo.class, value = "grupoConverter")
public class GrupoConverter implements Converter {
	
	private Grupos grupos;
	
	//forca uma injecao de dependencia
	public GrupoConverter() {
		grupos =  CDIServiceLocator.getBean(Grupos.class);
	}
	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Grupo grupo = null;
		if (value != null) {
			Long id = Long.valueOf(value);			
			grupo = grupos.porId(id);
		}
		
		return grupo;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value == null)
			return "";
		
		Grupo grupo = (Grupo) value;
		
		return grupo.getId() + "";
	}

}
