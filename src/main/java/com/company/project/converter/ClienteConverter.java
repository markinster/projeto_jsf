package com.company.project.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.company.project.entity.Cliente;
import com.company.project.repository.Clientes;
import com.markinster.dont.rename.util.cdi.CDIServiceLocator;

@FacesConverter (forClass = Cliente.class, value = "clienteConverter")
public class ClienteConverter implements Converter {
	
	private Clientes clientes;
	
	//forca uma injecao de dependencia
	public ClienteConverter() {
		clientes =  CDIServiceLocator.getBean(Clientes.class);
	}
	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Cliente cliente = null;
		if (value != null) {
			Long id = Long.valueOf(value);			
			cliente = clientes.porId(id);
		}
		
		return cliente;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value == null)
			return "";
		
		Cliente cliente = (Cliente) value;
		
		return cliente.getId() + "";
	}

}
